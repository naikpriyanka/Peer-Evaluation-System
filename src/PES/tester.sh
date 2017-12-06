mkdir PES;
mkdir PES/util;
mkdir PES/view;

javac util/*.java;
mv util/*.class PES/util;

javac -sourcepath PES/util/*.class view/*.java;

mv view/*.class PES/view;

java PES.view.PeerEvaluation;
