import socket
import re
import time
import threading
from _datetime import datetime

# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# Counter + Encoding standard
counter = -1
encoding = 'utf-8'
# Obtain IP
hostname = socket.gethostname()
myIP = socket.gethostbyname(hostname) # socket.gethostbyname(hostname) || Server
# Initialize variables
lastReceived = 0
address = 0
# Create dictionary for config file
config = dict()

# Handshake response on initinal request + our default message
threewayHandShakePartTwo = "com-" + str(counter + 1) + " accept " + myIP
defaultMessage = "I am server"

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

# Method to write to log file
def writeToLog(message):
    # If it does not exist, create file and write to, else append on already existing file
    file = open("Log.txt", "a")
    # Get time for log statement
    timeStamp = getTime()
    # Write to log timestamp + message, new line and indentation for easier reading
    file.write(timeStamp + "\n\t" + message + "\n")
    # Print the succesful log to console
    print("Logged entry succesfully at: " + timeStamp)

# Method to get the current time in
def getTime():
    # Use datetime to get time down to the second
    now = datetime.now()
    # Format it for a string
    formattedDateAndTime = now.strftime("%d-%m-%y %H:%M:%S")
    return formattedDateAndTime

# Method where we bind the bind the socket to the port
def socketBind():
    # Bind the socket to the port
    server_address = ('localhost', 10000)
    print('starting up on {} port {}'.format(*server_address))
    sock.bind(server_address)

# Method to send takes parameters a message, the server to send to and whether or not to print the message to ourselves
def send(message, address, printLine = True):
    sock.sendto(bytes(message, encoding), address)

    if printLine:
        message = "S:> " + message
        print(message)

# Method to receive, parameter printLine to print to ourselves, also keeps track of the time of last received message
def receive(printLine = True):
    global lastReceived, address
    data, address = sock.recvfrom(4096)
    message = data.decode(encoding)

    if printLine:
        print("C:> " + message)

    lastReceived = time.time()

    return message

# Method to handle 3 way handshake
def threeWayHandshake():
    global data, address
    # Get step 1, check if it conforms to protocol, split message into com check, counter check and ip check
    # ip check checks for 3 dots (improve with regEx?) , if it passes send back step 2, step 3 is likewise controlled
    # Log both succesful and unsuccesful handshake attempts, sends con-err if handshake fails to kill connection
    checkOne = receive()
    comCheck = checkOne[:3]
    counterCheck = checkOne[4:5]
    ipCheck = checkOne[6:]
    counter = 0
    for x in ipCheck:
        if x == ".":
            counter = counter + 1

    if comCheck == "com" and counterCheck == "0" and counter == 3:
        send(threewayHandShakePartTwo, address)
    else:
        errorMessage = "Unsuccesful handshake attempt"
        messageToLog = checkOne + "\n\t" + errorMessage
        writeToLog(messageToLog)
        send("con-err", address)

    checkTwo = receive()
    comCheck = checkTwo[:3]
    counterCheck = checkTwo[4:5]
    acceptCheck = checkTwo[6:]

    if comCheck == "com" and counterCheck == "0" and acceptCheck.startswith("accept"):
        messageToLog = checkOne + "\n\t" + threewayHandShakePartTwo + "\n\t" + checkTwo
        writeToLog(messageToLog)
        return True
    else:
        messageToLog = checkOne + "\n\t" + threewayHandShakePartTwo + "\n\t" + checkTwo
        writeToLog(messageToLog)
        send("con-err", address)
        return False


# Method to monitor last received message and check the time against the 4 second idle tolerance
def monitor():
    global lastReceived, address
    while True:
        time.sleep(0.2)
        currentTime = time.time()
        delta = currentTime - lastReceived

        if delta > 4:
            if connectionReset(address):
                break


# Method to send a "reset connection" message to client
def connectionReset(address):
    send("con-res 0xFE", address, False)  # 0xFE = 254
    message = receive()
    if message.endswith("0xFF"):  # 0xFF = 255
        print("Client disconnected")
        sock.close()
        return True
    return False

# Method to send and receive messages
def recieveAndSend():
    global data, address, counter
    messageCounter = 0
    currentSecond = 0

    # Infinite Loop, break condition hvis counter ikke matcher
    while True:
        # Modtag Data
        receivedMessage = receive()

        # Check for quit
        if receivedMessage == "quit":
            print("Client disconnected")
            sock.close()

        # Get current time, reset message counter and assign current time to x
        x = datetime.now().second
        if currentSecond != x:
            messageCounter = 0
            currentSecond = x

        # Increment message counter since we just got a message
        messageCounter = messageCounter + 1

        # Message spam limit, read from config
        limit = int(config.get("messages"))

        # If message counter exceeds spam limit, send a message and sleep receiving message for 3 seconds
        # Alternativt reset connection?
        if messageCounter >= limit:
            send("Spam detected, you can send messages again in 3 seconds", address)
            time.sleep(3)
            # if connectionReset(address): (Til at reset istedet for sleep)
            #    break

        # Checks for heartbeat and continues listening
        if receivedMessage.startswith("con-h"):
            continue

        # Regex for at finde counter
        m = re.search("msg-(\\d+)", receivedMessage)
        if m != None:
            receivedCounter = int(m.group(1))

            # Receive and print message, send default message back, else break and close socket
            # Check counter equals 1
            if receivedCounter - counter == 1:
                counter = receivedCounter + 1
                clearanceCheck = "res-" + str(counter) + "="
                # Send data back
                if receivedMessage:
                    send((clearanceCheck + defaultMessage), address)
            # Reset connection if counter doesnt match
            else:
                connectionReset(address)

# Bind socket, read config, run through 3 way handshake, if pass start monitor thread and run receive and send method

socketBind()
readConfig()
checkCleared = threeWayHandshake()
if checkCleared:
    thread = threading.Thread(target=monitor)
    thread.start()
    recieveAndSend()
else:
    # Should never hit this
    connectionReset(sock.recvfrom(4096))
    print("Handshake Failed")