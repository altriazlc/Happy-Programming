/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nhhag
 */
public class RequestSlotItem {
    private int requestSlotItemId;
    private int requestId;
    private int slotId;

    public RequestSlotItem(int requestSlotItemId, int requestId, int slotId) {
        this.requestSlotItemId = requestSlotItemId;
        this.requestId = requestId;
        this.slotId = slotId;
    }

    public RequestSlotItem() {
    }

    public int getRequestSlotItemId() {
        return requestSlotItemId;
    }

    public void setRequestSlotItemId(int requestSlotItemId) {
        this.requestSlotItemId = requestSlotItemId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
