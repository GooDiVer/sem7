class Customer(
    var money: Int,
    var curDevice: Device
) {
    fun hasApple() = curDevice.brand == "Apple"
    fun hasOldToNewDiscount(device: Device) = curDevice.name != device.name
}