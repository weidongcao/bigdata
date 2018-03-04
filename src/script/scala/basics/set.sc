//集合是无序集合类型的一个例子，所以集合不是序列。集合同样要求元素具有唯一性
val states = Set("Alabama", "Alaska", "Wyoming")
val lengths = states map (st => st.length)
val states2 = states + "Virginia"
val states3 = states2 + ("New York", "Illinois")