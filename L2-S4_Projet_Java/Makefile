JFLAGS = -g
JC = javac
OPT = -O3
#OPT = -g
WARN = -Wall


cls: src/game/play/*.java src/game/character/*.java src/game/board/*.java src/game/board/tile/*.java src/game/board/resource/*.java src/game/exception/*.java src/game/*.java src/game/play/player/*.java
	$(JC) $^ -d classes

clean:
	$(RM) classes/game/*.class classes/game/play/*.class classes/game/character/*.class classes/game/exception/*.java classes/game/board/*.class classes/game/board/tile/*.class classes/game/board/resource/*.class classes/game/play/player/*.class

doc: src/game/play/*.java src/game/character/*.java src/game/board/*.java src/game/board/tile/*.java src/game/board/resource/*.java src/game/play/player/*.java src/game/exception/*.java
	javadoc -d $@ $^

agricole.jar: 
	cd classes ; jar -cvmf ../meta-inf/MANIFESTAgricole.MF ../jar/agricole.jar game

guerre.jar: 
	cd classes ; jar -cvmf ../meta-inf/MANIFESTGuerre.MF ../jar/guerre.jar game

agricoleHuman.jar: 
	cd classes ; jar -cvmf ../meta-inf/MANIFESTAgricoleH.MF ../jar/agricoleHuman.jar game

guerreHuman.jar: 
	cd classes ; jar -cvmf ../meta-inf/MANIFESTGuerreH.MF ../jar/guerreHuman.jar game