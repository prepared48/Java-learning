
 注意：这里打印出来的对象是一样的 是toString()的问题
 simpledateformat对象的toString()，继承自Object的toString，重写了hashcode方法，导致变量的hashcode一致
 使用的是patter的hashcode，所以是一样，应为pattern是一样的

simpleDateFormat 的hashcode方法

```
@Override
 public int hashCode()
 {
    return pattern.hashCode();
     // just enough fields for a reasonable distribution
 }
```
    
    
 