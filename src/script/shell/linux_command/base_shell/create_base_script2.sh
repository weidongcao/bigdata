#!/bin/bash
# 测试使用方括号进行数学运算时对浮点数的支持
var1=100
var2=45
var3=$[$var1 / $var2]
echo The Final Result Is $var3
