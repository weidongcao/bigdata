#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.4.3 创建输入文件描述符

# 可以用和重定向输出文件描述符同样的办法重定向输入文件描述符。
# 在重定向到文件之前，先将STDOUT文件描述符保存到另外一个文件描述符，
# 然后在读取完文件之后再将STDIN恢复到它原来的位置。

exec 6<&0

exec 0< testfile

count=1
while read line
do
	echo "Line #$count: $line"
	count=$[ $count + 1 ]
done
exec 0<&6
read -p "Are you done now? " answer
case $answer in
	Y|y)	echo "Goodbye";;
	N|n)	echo "Sorry, this is the end.";;
esac

# 在这个例子中，文件描述符6用来保存STDIN的位置。然后脚本将STDIN重定向到一个文件。
# read命令的所有输入都来自重定向生的STDIN(也就是输入文件)
# 在读取了所有行之后，脚本会将STDIN重定向到文件描述符6，从而将STDIN恢复到原来的位置。
# 该脚本用了另外一个read命令来测试STDIN是否恢复正常了。这将它会造物键盘的输入。

