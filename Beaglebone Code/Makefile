all: wifi
  
wifi: compile
	scp -r output/ debian@beaglebone.local:/home/debian
usb: compile
	scp -r output/ debian@192.168.7.2:/home/debian
compile:
	./gradlew build