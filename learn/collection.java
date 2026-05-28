public class Vector<E>
    extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{

    // 获取指定索引处的元素
    public synchronized E get(int index) {
        if (index >= elementCount) // 如果索引超出了列表的大小，则抛出数组下标越界异常
            throw new ArrayIndexOutOfBoundsException(index);

        return elementData(index); // 返回指定索引处的元素
    }

    // 移除指定索引处的元素
    public synchronized E remove(int index) {
        modCount++; // 修改计数器，标识列表已被修改
        if (index >= elementCount) // 如果索引超出了列表的大小，则抛出数组下标越界异常
            throw new ArrayIndexOutOfBoundsException(index);
        E oldValue = elementData(index); // 获取指定索引处的元素

        int numMoved = elementCount - index - 1; // 计算需要移动的元素个数
        if (numMoved > 0) // 如果需要移动元素
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved); // 将数组中的元素向左移动一位
        elementData[--elementCount] = null; // 将最后一个元素设置为 null，等待垃圾回收

        return oldValue; // 返回被移除的元素
    }
}