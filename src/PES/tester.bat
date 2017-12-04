@echo OFF
mkdir PES;
mkdir PES/util;
mkdir PES/view;

javac util/*.java;
move util/*.class PES/util;

javac -sourcepath PES/util/*.class view/*.java;

move view/*.class PES/view;

java PES.view.PeerEvaluation;
