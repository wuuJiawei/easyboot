package com.easyboot.common.ztree;

import java.io.Serializable;
import java.util.List;

/**
 * \*
 * \* @name: ZTree
 * \* @author: 武佳伟丶
 * \* @date: 2018/4/9 0009
 * \* @time: 17:11
 * \* @description: To change this template use File | Settings | File Templates.
 * \
 */
public class ZTree implements Serializable {

    private int id;
    private String name;
    private boolean open;
    private List<ZTree> children;
    private boolean isParent;
    private String url;
    private String title;
    private boolean checked;
    private int level;
    private int check_Child_State;
    private boolean check_Focus;
    private boolean checkedOld;
    private boolean dropInner;
    private boolean drag;

    public boolean isDropInner() {
        return dropInner;
    }

    public void setDropInner(boolean dropInner) {
        this.dropInner = dropInner;
    }

    public boolean isDrag() {
        return drag;
    }

    public void setDrag(boolean drag) {
        this.drag = drag;
    }

    public int getCheck_Child_State() {
        return check_Child_State;
    }

    public void setCheck_Child_State(int check_Child_State) {
        this.check_Child_State = check_Child_State;
    }

    public boolean isCheck_Focus() {
        return check_Focus;
    }

    public void setCheck_Focus(boolean check_Focus) {
        this.check_Focus = check_Focus;
    }

    public boolean isCheckedOld() {
        return checkedOld;
    }

    public void setCheckedOld(boolean checkedOld) {
        this.checkedOld = checkedOld;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<ZTree> getChildren() {
        return children;
    }

    public void setChildren(List<ZTree> children) {
        this.children = children;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}