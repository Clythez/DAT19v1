import socket
import re
import threading
import time
import sys

# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# Counter + Encoding standard
counter = 0
encoding = 'utf-8'
# Obtain IP
hostname = socket.gethostname()
myIP = socket.gethostbyname(hostname)  # socket.gethostbyname(hostname) || Client
# Connection Info
server_address = ('localhost', 10000)
lastSent = 0

# Step 1 + 3 of Three Way Handshake
threewayHandShakePartOne = "com-" + str(counter) + " " + myIP
threewayHandShakePartThree = "com-" + str(counter) + " accept"

config = dict()

# Method to read the config
def readConfig():
    # Global keyword to access and modify the config dictionary
    global config
    # Open opt.conf in read mode
    file = open("opt.conf", "r")
    x = file.readline()
    # Split config lines on : into key and value and add them to dictionary
    while x is not "":
        split = x.split(":")
        key = split[0].strip().lower()
        value = split[1].strip().lower()
        config[key] = value

        x = file.readline()

# Method to send takes parameters a message, the server to send to and whether or not to print the message to ourselves
def send(message, address, printLine=True):
    global lastSent
    sock.sendto(bytes(message, encoding), address)
    lastSent = time.time()

    if printLine:
        message = "S:> " + message
        print(message)

# Method to receive, parameter printLine to print to ourselves, also keeps track of the time of last received message
def receive(printLine=True):
    global data, server, lastSent
    # Set timeout if no response from server in 3 seconds (specific work around after handshake)
    sock.settimeout(3)
    try:
        data, server = sock.recvfrom(4096)
        message = data.decode(encoding)

        if printLine:
            print("C:> " + message)

        lastSent = time.time()

        return message
    except:
        print("Timeout")
        return "con-TO"


# Method to send a heart beat if we havent send a message in the last 3 seconds
def heartBeat():
    global lastSent, server_address
    while True:
        time.sleep(0.2)
        currentTime = time.time()
        if (lastSent + 3) < currentTime:
            if config.get("keepalive") == "true":
                send("con-h 0x00", server_address, False)

# SpamBot to test servers spam limit
def spamBot():
    global server_address
    while True:
        send("Spam", server_address, False)

# Initiate 3 way handshake, send step 1, receive step 2, send step 3
def sendGetThreeWayHandshake():
    global threewayHandShakePartOne, threewayHandShakePartThree

    send(threewayHandShakePartOne, server_address)
    message = receive()
    if message.startswith("con-err"):
        print("Handshake failed")
        sock.close()
    else:
        send(threewayHandShakePartThree, server_address)
        message = receive()
        if message.startswith("con-err"):
            print("Handshake failed")
            sock.close()
        elif message.startswith("con-TO"):
            return True
        else:
            return True

    return False


# Method to send and receive messages
def sendAndReceive():
    global sent, data, server, counter
    # Infinite Loop
    while True:
        # Set the protocol & Enter a message
        clearanceCheck = "msg-" + str(counter) + "="
        message = input("Enter a message: ")
        try:
            # Send data
            if message == "quit":
                send("Client disconnecting", server_address)
                sock.close()
                sys.exit()

            send(clearanceCheck + message, server_address)

            # Receive response
            receivedMessage = receive()

            # Reset connection if response is con-res
            if receivedMessage.startswith("con-res"):
                send("con-res 0xFF", server_address)
                sock.close()
                break

            # Regex to find counter and check if correct
            m = re.search("res-(\\d+)", receivedMessage)
            receivedCounter = int(m.group(1))

            if receivedCounter - counter == 1:
                counter = receivedCounter + 1

        finally:
            print("")
            # sock.close()

# read config, initiate handshake, start heartbeat, check spambot config, start/not spambot, run send and receive
readConfig()
handshakeCheck = sendGetThreeWayHandshake()
if handshakeCheck:
    thread = threading.Thread(target=heartBeat)
    thread.start()
    if config.get("spambot") == "true":
        thread1 = threading.Thread(target=spamBot)
        thread1.start()
    sendAndReceive()
else:
    print("Shutting down")
    sock.close()
