#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第16章 控制脚本
# 16.6.1 用at命令来计划执行作业
# Page 348

echo "This script run at $(date +%B%d,%T)" > time_at.out
echo >> time_at.out

sleep 5
echo "This is the script's end..." >> time_at.out
