all: PlayerMovement.java
	@javac $<

run: all
	@java PlayerMovement

clean:
	@rm *.class

help: .makehelp
	@cat < .makehelp

