#!/bin/bash
# break 跳出外部循环,使用方式如下:
# break n
# n指定了跳出的循环层级。默认情况下，n为1，表明跳出的是当前的循环
# 如果你将n设为2，break命令就会停止下一组的外部循环。

for (( a = 1; a < 4; a++))
do
	echo "Outer loop: $a"
	for (( b = 1; b < 100; b++ ))
	do	
		if [ $b -gt 4 ];then
			break 2
		fi
		echo "	Inner loop: $b"
	done
done
