#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第18章 图形化桌面环境中的脚本编程
# 18.1 创建文本菜单
# Page 379
function menu {

	clear
	echo 
	echo -e "\t\t\tSys Admin Menu\n"
	echo -e "\t1. Display disk space"
	echo -e "\t3. Display memory usage"
	echo -e "\t0. Exit menu\n\n"
	echo -en "\t\tEnter option: "
	read -n 1 option
}
function diskspace {
	clear
	df -k
}
function whoseon {
	clera
	who
}
function memusage {
	clear
	cat /proc/meminfo
}

while [ 1 ]
do

	menu
	case $option in
	0)
		break ;;
	1) 
		diskspace ;;
	2)
		whoseon ;;
	3)
		memusage ;;
	*)
		clear
		echo "Sorry, wrong selection" ;;
	esac
	echo -en "\n\n\t\t\tHit any key to continue"
	read -n 1 line
done
clear

