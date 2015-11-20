all:
	javac -d . src/*.java
	java -Djava.library.path=lib  src.gpuSimulator model/model.txt 5 1
	rm */*.class
