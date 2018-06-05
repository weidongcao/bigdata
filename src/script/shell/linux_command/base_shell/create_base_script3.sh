#!/bin/bash
# 在bc命令中测试使用变量
var1=20
var2=3.1415
var3=$(echo "scale=4; $var1 * $var1" | bc)
var4=$(echo "scale=4; $var3 * $var2" | bc)

echo var3 = $var3
echo var4 = $var4
