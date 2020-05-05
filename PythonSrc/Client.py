import socket
import re

# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# Counter + Encoding standard
counter = 0
encoding = 'utf-8'
# Obtain IP
hostname = socket.gethostname()
myIP = "Client" #socket.gethostbyname(hostname)
# Connection Info
server_address = ('localhost', 10000)

# Step 1 + 3 of Three Way Handshake
threewayHandShakePartOne = "com-" + str(counter) + " <" + myIP + ">"
threewayHandShakePartThree = "com-" + str(counter) + " accept"

# Initiate 3 way handshake, send step 1, receive step 2, send step 3
sent = sock.sendto(bytes(threewayHandShakePartOne, encoding), server_address)
print(threewayHandShakePartOne)
data, server = sock.recvfrom(4096)
print(data.decode(encoding))
sent = sock.sendto(bytes(threewayHandShakePartThree, encoding), server_address)
print(threewayHandShakePartThree)

# Infinite Loop
while True:
    # Set the protocol & Enter a message
    clearanceCheck = "msg-" + str(counter) + "="
    message = input("Enter a message: ")
    try:
        # Send data
        print("C:> " + clearanceCheck + message)
        sent = sock.sendto(bytes(clearanceCheck + message, encoding), server_address)

        # Receive response
        data, server = sock.recvfrom(4096)
        receivedMessage = data.decode(encoding)

        # Regex to find counter and check if correct
        m = re.search("res-(\\d+)", receivedMessage)
        receivedCounter = int(m.group(1))

        if receivedCounter - counter == 1:
            counter = receivedCounter + 1
            print("S:> " + receivedMessage)

    finally:
        print("")
        #sock.close()