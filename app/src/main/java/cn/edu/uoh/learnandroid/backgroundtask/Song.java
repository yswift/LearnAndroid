package cn.edu.uoh.learnandroid.backgroundtask;

public class Song {
    public long id;//歌曲id
    public String title;//歌曲名
    public String singer;//歌手
    public long size;//歌曲所占空间大小
    public int duration;//歌曲时间长度
    public String path;//歌曲地址
    public long  albumId;//图片id

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", singer='" + singer + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                ", path='" + path + '\'' +
                ", albumId=" + albumId +
                '}';
    }
}
