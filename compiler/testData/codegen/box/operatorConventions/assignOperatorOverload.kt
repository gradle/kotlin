
// TARGET_BACKEND: JVM_IR

var result: String = "Fail"

operator fun Int.assign(other: String) {
    result = other
}
operator fun Int.assign(other: Int) {
    result = "OK.Int.assign(Int)"
}
operator fun Container.assign(other: Int) {
    this.value = "OK"
}
operator fun Container.set(i: Int, v: Int) {
    this.value = "OK.Container.set1"
}
operator fun Container.set(i: Int, v: Long) {
    this.value = "OK.Container.set2"
}

data class Foo(val x: Container)
data class Container(var value: String)

var nullCheckResult: String = "OK"
data class NullCheck(val x: NullCheckContainer)
data class NullCheckContainer(var value: String)
operator fun NullCheckContainer.assign(value: String) {
    nullCheckResult = value
}

fun box(): String {
    // Test simple assign for local variable
    val x = 10
    x = "OK"
    if (result != "OK") return "Fail: $result"

    // Test same type assign overload
    x = 5
    if (result != "OK.Int.assign(Int)") return "Fail: $result"

    // Test assign overload for var is not applied
    result = "OK.var"
    var y = 10
    y = 5
    if (result != "OK.var" || y != 5) return "Fail: $result, y = $y"

    // Test simple assign for property
    val foo = Foo(Container("Fail"))
    foo.x = 42
    if (foo.x.value != "OK") return "Fail: ${foo.x.value}"

    // Test set() has priority
    foo.x[1] = 2
    if (foo.x.value != "OK.Container.set1") return "Fail: ${foo.x.value}"
    foo.x[1] = 2L
    if (foo.x.value != "OK.Container.set2") return "Fail: ${foo.x.value}"

    // Test assign() on null is not called
//    val nullCheck: NullCheck? = null
//    nullCheck?.x = "Fail"
//    if (nullCheckResult != "OK") return "Fail: $nullCheckResult"

    return "OK"
}
