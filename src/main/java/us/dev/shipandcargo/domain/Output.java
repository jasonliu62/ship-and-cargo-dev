package us.dev.shipandcargo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Output {

    private Long groupId;
    private Long id;
    // 实际：actual
    // 虚拟: virtual
    // 即期: spot
    private Float actual_own;
    private Float actual_rent;
    private Float actual_all;
    private Float virtual_own;
    private Float virtual_rent;
    private Float virtual_all;
    private Float spot_own;
    private Float spot_rent;
    private Float spot_all;
    private Float all;
    private Long uploaderId;

    // 创建完output以后，记得把所有指数都set为0


    public void initOutput() {
        this.actual_own = 0F;
        this.actual_rent = 0F;
        this.actual_all = 0F;
        this.virtual_own = 0F;
        this.virtual_rent = 0F;
        this.virtual_all = 0F;
        this.spot_own = 0F;
        this.spot_rent = 0F;
        this.spot_all = 0F;
        this.all = 0F;
    }

}
