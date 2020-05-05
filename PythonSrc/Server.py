import socket
import re
import time

# Create a UDP socket

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# Counter + Encoding standard
counter = -1
encoding = 'utf-8'
# Obtain IP
hostname = socket.gethostname()
myIP = "Server" #socket.gethostbyname(hostname)

threewayHandShakePartTwo = "com-" + str(counter + 1) + " accept <" + myIP + ">"
defaultMessage = "I am server"

# Bind the socket to the port
server_address = ('localhost', 10000)
print('starting up on {} port {}'.format(*server_address))
sock.bind(server_address)
# Set timeout to 4 seconds
# sock.settimeout(4)

# Receive Step 1 + 3 of 3 way handshake, send step 2
data, address = sock.recvfrom(4096)
print(data.decode(encoding))
sock.sendto(bytes(threewayHandShakePartTwo, encoding), address)
print(threewayHandShakePartTwo)
data, address = sock.recvfrom(4096)
print(data.decode(encoding))

# Infinite Loop, break condition hvis counter ikke matcher
while True:
    start = time.time()
    # Modtag Data
    data, address = sock.recvfrom(4096)
    receivedMessage = data.decode(encoding)

    # Regex for at finde counter
    m = re.search("msg-(\\d+)", receivedMessage)
    receivedCounter = int(m.group(1))

    # Receive and print message, send default message back, else break and close socket
    if receivedCounter - counter == 1:
        counter = receivedCounter + 1
        print("C:> " + receivedMessage)
        clearanceCheck = "res-" + str(counter) + "="
        # Send data back
        if data:
            sent = sock.sendto(bytes(clearanceCheck + defaultMessage, encoding), address)
            print("S:> " + clearanceCheck + defaultMessage)
            end = time.time()
            print(end - start)

    else:
        errorMessage = "Your counter clearance check did not pass"
        print(errorMessage)
        sent = sock.sendto(bytes(errorMessage, encoding), address)