package structural

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class DisplayDataType(
    val index: Float,
    val datta: String
)

class DataDisplay {
    fun displayData(datta: DisplayDataType) {
        println("Data is displayed: ${datta.index} - ${datta.datta}")
    }
}

data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 7))
        list.add(DatabaseData(4, 23))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(datta: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(datta: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for (dat in datta) {
            val ddt = DisplayDataType(dat.position.toFloat(), dat.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}

class AdapterTest {
    @Test
    fun adapterTest() {
        val generatedData = DatabaseDataGenerator().generateData()
        val convertData = DataDisplayAdapter(DataDisplay()).convertData(generatedData)
        assertThat(convertData.size).isEqualTo(3)
        assertThat(convertData[1].index).isEqualTo(3f)
    }
}