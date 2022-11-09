package creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

interface DataSource

class DatabaseDataSource : DataSource

class NetworkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T: DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource =
        DatabaseDataSource()
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource =
        NetworkDataSource()
}

class AbstractFactoryTest {

    @Test
    fun testAbstractFactory() {
        val dataSourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>().makeDataSource()
        println("Created dataSource: $dataSourceFactory")
        assertThat(dataSourceFactory).isInstanceOf(DatabaseDataSource::class.java)
    }
}