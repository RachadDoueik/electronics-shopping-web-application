package mytechshop.mytechshop.enums;

public enum CartStatus {
    ACTIVE,    // Cart is being used, items can be added or removed
    ORDERED,   // Cart has been checked out (ordered)
    ABANDONED  // Cart was not checked out, possibly abandoned
}

