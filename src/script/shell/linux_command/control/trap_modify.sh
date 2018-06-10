#!/bin/bash
# <Linux命令行与Shell脚本编程大全> 第16章 控制脚本
# 要想在脚本中的不同位置进行不同的捕获处理,只需要重新使用带有新选项的trap命令

trap "echo ' Sorry... Ctrl - C is trapped.'" SIGINT

count=1
while [ $count -le 8 ]
do
	echo "Loop #$count"
	sleep 2
	count=$[ $count + 2 ]
done

trap "echo ' I modified the trap!'" SIGINT

count=1
while [ $count -le 8 ]
do
	echo "Second Loop #$count"
	sleep 2
	count=$[ $count + 1 ]
done

# 也可以删除已设置好的捕获,只需要在trap命令与希望恢复默认行为的信号列表之间加上两个破折号就行了
# 也可以在trap命令后使用单破折号来恢复信号的默认行为.单破折号和双破折号都可以正常发挥作用
trap - SIGINT

echo " I just removed the trap"
count=1
while [ $count -le 8 ]
do
	echo "Third Loop #$count"
	sleep 2
	count=$[ $count + 1 ]
done

