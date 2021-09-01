package cc.core.io.randomaccessfile;

import java.io.*;


/**
 * Yukino
 * 2020/3/12
 */
public class LastLineInFileTools
{
    /**
     * 获取文本文件最后一行中的字符串。
     *
     * @param file
     *            目标文件
     * @param charset
     *            字符编码
     * @return 文本文件中最后一行中的字符串。
     */
    public static String getLastLineStr(File file, String charset)
    {
        String lastLine = null;
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(file, "rwd");
            long lastLinePos = getLastLinePos(raf);
            long length = raf.length();
            byte[] bytes = new byte[(int) ((length - 1) - lastLinePos)];
            raf.read(bytes);

            if (charset == null)
            {
                // return new String(bytes);
                lastLine = (new String(bytes));
            } else
            {
                // return new String(bytes, charset);
                lastLine = (new String(bytes, charset));
            }

        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lastLine;
    }
    /**
     * 在最后一行中末尾中插入文本。
     *
     * @param file
     *            目标文件
     * @param newLastLine
     *            即将插入的文本
     * @param charset
     *            字符编码名称
     */
    public static void insertAfterLastLine(File file, String newLastLine,
                                           String charset)
    {
        newLastLine = "\n" + newLastLine;
        insertInLastLine(file, newLastLine, charset);
    }
    /**
     * 更新最后一行文本。
     *
     * @param file
     *            目标文件
     * @param newLastLine
     *            替换文本
     * @param charset
     *            字符编码名称
     */
    public static void updateLastLine(File file, String newLastLine,
                                      String charset)
    {
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(file, "rwd");
            // 删除最后一行文本
            deleteLastLine(raf);
            // 在最后一行插入新的一行文本
            insertInLastLine(newLastLine, charset, raf);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            if (raf != null)
            {
                try
                {
                    raf.close();
                } catch (IOException e)
                {
                }
            }
        }
    }
    /**
     * 在文本文件最后一行末尾追加文本。
     *
     * @param newLastLine 要追加的文本
     * @param charset 字符编码名称
     * @param raf RandomAccessFile表示的文件
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private static void insertInLastLine(String newLastLine, String charset,
                                         RandomAccessFile raf)
            throws IOException, UnsupportedEncodingException
    {
        // 移动指针到最后一行
        raf.seek(raf.length());
        raf.write(newLastLine.getBytes(charset));
    }
    /**
     * 在文本文件最后一行末尾插入文本。
     *
     * @param file
     *            目标文件
     * @param newLastLine
     *            要插入的文本
     * @param charset
     *            字符编码名称
     */
    public static void insertInLastLine(File file, String newLastLine,
                                        String charset)
    {
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(file, "rwd");
            // 移动指针到最后一行
            raf.seek(raf.length());
            raf.write(newLastLine.getBytes(charset));
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            if (raf != null)
            {
                try
                {
                    raf.close();
                } catch (IOException e)
                {
                }
            }
        }
    }
    /**
     * 删除本文文件最后一行。
     * @param raf
     * @throws IOException
     */
    private static void deleteLastLine(RandomAccessFile raf) throws IOException
    {
        // 获取最后一行的位置
        long lastLinePos = getLastLinePos(raf);
        // 删除最后一行
        raf.setLength(lastLinePos);
    }

    /**
     * 删除本文文件最后一行。
     * @param file
     */
    public static void deleteLastLine(File file)
    {
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(file, "rwd");
            // 获取最后一行的位置
            long lastLinePos = getLastLinePos(raf);
            // 删除最后一行
            raf.setLength(lastLinePos);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            if (raf != null)
            {
                try
                {
                    raf.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
    /**
     * 返回最后一行的起始位置,并移动文件指针到最后一行的起始位置。
     * @param raf RandomAccessFile对象
     * @return 最后一行的起始位置
     * @throws IOException
     */
    private static long getLastLinePos(RandomAccessFile raf) throws IOException
    {
        long lastLinePos = 0L;
        // 获取文件占用字节数
        long len = raf.length();
        if (len > 0L)
        {
            // 向前走一个字节
            long pos = len - 1;
            while (pos > 0)
            {
                pos--;
                // 移动指针
                raf.seek(pos);
                // 判断这个字节是不是回车符
                if (raf.readByte() == '\n')
                {
                    // lastLinePos = pos;// 记录下位置
                    // break;// 前移到会第一个回车符后结束
                    return pos;
                }

            }
        }
        return lastLinePos;
    }
    /**
     * 返回文本文件的最后一行的起始位置。
     * @param file 文本文件
     * @return 最后一行的下标
     */
    public static long getLastLinePos(File file)
    {
        long lastLinePos = 0L;
        RandomAccessFile raf;
        try
        {
            raf = new RandomAccessFile(file, "r");

            // 获取文件占用字节数
            long len = raf.length();
            if (len > 0L)
            {
                // 向前走一个字节
                long pos = len - 1;
                while (pos > 0)
                {
                    pos--;
                    // 移动指针
                    raf.seek(pos);
                    // 判断这个字节是不是回车符
                    if (raf.readByte() == '\n')
                    {
                        lastLinePos = pos;// 记录下位置
                        break;// 前移到会第一个回车符后结束
                        // return pos;
                    }
                }
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lastLinePos;
    }
}