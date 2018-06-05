#!/bin/bash
# 测试bc命令在内联输入重定向下的使用
var1=3.14
var2=2.5
var3=16
var4=2.7

var5=$(bc << EOF
scale = 4
a1 = ( $var1 * $var2)
a2 = ($var3 * $var4)
a1 + a2
EOF
)

echo var5 = $var5
