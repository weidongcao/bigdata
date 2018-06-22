#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第22章 gawk进阶
# 22.8 实例
# Page: 492
# 利用gawk进行数据计算
# 有一个数据文件，其中包含了再去队伍(每队两句选手)的保龄球比赛得分情况
# 每位选手都有3场比赛的成绩，这些成绩都保存在数据文件中，每位选手由位于第二列的队名来标识
# score.txt数据文件内容:
# Rich Blum,team1,100,115,95
# Barbara Blum,team1,110,115,100
# Christine Bresnahan,team2,120,115,118
# Tim Bresnaha,team2,125,112,116

for team in $(gawk -F, '{print $2}' score.txt | uniq)
do
	gawk -v team=$team 'BEGIN{FS=","; total=0} {
		if ($2 == team){
			total += $3 + $4 + $5;
		}
	} 
	END {
		avg = total / 6;
		print "Total for", team, "is", total, ", the average is", avg
	}' score.txt
done

# for 循环中的第一条语句过滤出数据文件中的队名，然后使用uniq命令返回不重复的队名。
# for循环再对每个队进行迭代。
# for循环内部的gawk语句进行计算操作。对于每一条记录，首先确定除名是否和正在进行循环的除名相符。
# 这是通过利用gawk的-v选项来实现的，该选项允许我们在gawk程序中传递shell变量。
# 如果队名相符，代码会对数据记录中的石块比赛得分求和然后将每条记录的值再相加，只要数据记录属于同一队。
# 在循环迭代的结尾处，gawk代码会显示出总分以及平均分。输出结果如下：

