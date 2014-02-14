@echo off

set path7zip=C:\Soft\sevenZip
path = %path7zip%;%path%

del PokerFace.jar
cd ..

cd bin
7z a -tzip ../deploy/PokerFace.jar -r *.*

rem pause