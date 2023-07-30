package com.example.and2_assignmentfinal.Model;

import java.util.ArrayList;
import java.util.List;

public class Avatar {

    private String NameAV;
    private String URl;

    public Avatar( String nameAV, String URl) {

        NameAV = nameAV;
        this.URl = URl;
    }

    public Avatar() {
    }
    public String getNameAV() {
        return NameAV;
    }

    public void setNameAV(String nameAV) {
        NameAV = nameAV;
    }

    public String getURl() {
        return URl;
    }

    public void setURl(String URl) {
        this.URl = URl;
    }

    public List<Avatar> getListAva() {
        List<Avatar> ListAva = new ArrayList<>();
        ListAva.add(new Avatar("Snack Bí Đỏ","https://i.pinimg.com/236x/f5/da/31/f5da31c40c390672cbe348e213e4c182.jpg"));
        ListAva.add(new Avatar("Potato Fries","https://i.pinimg.com/236x/80/db/28/80db28bb069c3ee3182c16cc92c16479.jpg"));
        ListAva.add(new Avatar("Lay's Doritos","https://i.pinimg.com/236x/5b/6c/16/5b6c16623e3997f6a52916310da66a8b.jpg"));
        ListAva.add(new Avatar("Lay's Salt & Vinegar","https://i.pinimg.com/236x/a1/e9/d1/a1e9d181d931d7beba82cabddd9a9024.jpg"));
        ListAva.add(new Avatar("Lay's Scary Attic Noises","https://i.pinimg.com/236x/f7/4e/ad/f74eadca5a870225ba5994677e459316.jpg"));
        ListAva.add(new Avatar("Prawn Crackers","https://i.pinimg.com/236x/be/71/dd/be71ddbe013f627eaf9938f9d5033518.jpg"));
        ListAva.add(new Avatar("Thập Cẩm","https://i.pinimg.com/236x/bf/cd/79/bfcd79b529805592173227c4ed2ecb18.jpg"));
        return ListAva;
    }
}
