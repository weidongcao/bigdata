#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第21章 sed进阶
# 21.6.2 重定向sed的输出
# 默认情况下，sed编辑器会将脚本的结果输出到STDOUT上。
# 你可以在Shell脚本中使用各种标准方法对sed编辑器的输出进行重定向。
# 可以在脚本中用$()将sed编辑器命令的输出重定向到一个变量中，以备后用。
# 下面的例子使用sed脚本来向数值计算结果添加逗号。

factorial=1
counter=1
number=$1

while [ $counter -le $number ]
do
	factorial=$[ $factorial * $counter ]
	counter=$[ $counter + 1 ]
done

result=$(echo $factorial | sed '{
:start
s/\(.*[0-9]\)\([0-9]\{3\}\)/\1,\2/
t start
}')

echo "The result is $result"

# 在使用普通的阶乘计算脚本后，脚本的结果会被作为sed编辑器脚本的输入，它会给结果加上逗号，
# 然后echo语句使用这个值产生最终结果
