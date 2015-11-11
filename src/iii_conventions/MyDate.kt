package iii_conventions


data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return 12 * 31 * ( this.year - other.year) + 31 * ( this.month - other.month) + (this.dayOfMonth - other.dayOfMonth)
    }

    operator fun plus(timeInterval: TimeInterval): MyDate {
        return when (timeInterval) {
            TimeInterval.DAY -> return MyDate(year, month, dayOfMonth + 1)
            TimeInterval.WEEK -> MyDate(year, month, dayOfMonth +7)
            TimeInterval.YEAR -> MyDate(year + 1, month, dayOfMonth)
            else -> this;
        }
    }

    operator fun plus(repeatableTimeInterval: RepeatableTimeInterval) : MyDate{
        return addTimeIntervals(repeatableTimeInterval.interval,repeatableTimeInterval.times);
    }

}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterator<MyDate>, Range<MyDate> {

    override fun contains(item: MyDate): Boolean {
        return start <= item && item <= end;
    }

    var currentDate = start;
    override fun next(): MyDate {
        val toReturn = currentDate;
        currentDate = currentDate.nextDay();
        return toReturn;
    }

    override fun hasNext(): Boolean {
        return currentDate <= end
    }

}

operator fun MyDate.rangeTo(other: MyDate): Iterator<MyDate> {
    return DateRange(this, other);
}

operator fun <MyDate> Iterator<MyDate>.contains(date: MyDate): Boolean {
    while (hasNext()) {
        if (next() == date) {
            return true
        }
    }
    return false
}

class RepeatableTimeInterval( val interval: TimeInterval, val times:Int){

}


operator fun TimeInterval.times(i: Int): RepeatableTimeInterval {
    return RepeatableTimeInterval(this,i);
}