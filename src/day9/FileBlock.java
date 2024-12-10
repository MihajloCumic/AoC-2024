package day9;

public class FileBlock {
    int id;
    boolean isEmpty;

    private FileBlock(int id, boolean isEmpty){
        this.id = id;
        this.isEmpty = isEmpty;
    }

    static FileBlock createEmptyBlock(){
        return new FileBlock(-1, true);
    }

    static FileBlock createBlock(int id){
        return new FileBlock(id, false);
    }
}
