mkdir  -p out/PES/util;
mkdir -p out/PES/view;

cd out;

javac ../src/PES/util/*.java;
mv ../src/PES/util/*.class PES/util;


javac -sourcepath PES/util/*.class ../src/PES/view/*.java;

mv ../src/PES/view/*.class PES/view;

java PES.view.PeerEvaluation;
