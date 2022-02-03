JFLAGS = -g
JC = javac
OPT = -O3
#OPT = -g
WARN = -Wall


cls: src/game/competitor/*.java src/game/competition/*.java src/game/match/*.java src/game/util/*.java src/game/*.java src/game/match/strategy/*.java src/game/exception/*.java src/game/competition/strategy/generatesGroupStrategy/*.java src/game/competition/strategy/qualifiedStrategy/*.java src/game/competition/observer/*.java
	$(JC) $^ -d classes

test: test/game/competitor/*.java test/game/competition/*.java test/game/match/*.java test/game/match/strategy/*.java test/game/competition/strategy/generatesGroupStrategy/*.java test/game/competition/strategy/qualifiedStrategy/*.java test/game/competition/observer/*.java
	$(JC) -classpath test4projet.jar $^

jar: 
	mkdir jar;
	cd classes; jar -cvmf ../meta-inf/MANIFEST.MF ../jar/game.jar game

clean: classes/game/competitor/*.class classes/game/competition/*.class classes/game/match/*.class classes/game/util/*.class classes/game/*.class classes/game/match/strategy/*.class classes/game/exception/*.class classes/game/competition/strategy/generatesGroupStrategy/*.class classes/game/competition/strategy/qualifiedStrategy/*.class classes/game/competition/observer/*.class
	$(RM) $^

doc: src/game/competitor/*.java src/game/competition/*.java src/game/match/*.java src/game/util/*.java src/game/match/strategy/*.java src/game/competition/strategy/generatesGroupStrategy/*.java src/game/competition/strategy/qualifiedStrategy/*.java src/game/competition/observer/*.java
	javadoc -d $@ $^
