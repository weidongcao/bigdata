#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第18章 图形化桌面环境中的脚本编程
# bash shell提供了一个很容易上手的小工具，帮助我们自动完成建立菜单而已和获取用户输入
# select命令只需要一条命令就可以创建出菜单，然后获取输入的答案并自动处理
# select命令的格式如下:
# select variable in list
# do
# 	commands
# done
# 
# list参数是由空格分隔的文本选项列表
# 这些列表构成了整个菜单
# select命令会将每个列表项显示成一个带编号的选项
# 然后为选项显示一个由PS3环境变量定义的特殊提示符

function diskspace {
	clear
	df -k
}
function whoseon {
	clear
	who
}
function memusage {
	clear
	cat /proc/meminfo
}
PS3="Enter option:"
select option in "Display disk space" "Display logged on users" "Display memory usage" "Exit program"
do
	case $option in
	"Exit program")
		break ;;
	"Display disk space")
		diskspace ;;
	"Display logged on users")
		whoseon ;;
	"Display memory usage")
		memusage ;;
	*)
		clear
		echo "Sorry, wrong selection" ;;
	esac
done
clear

