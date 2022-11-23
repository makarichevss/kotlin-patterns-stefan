package behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface ReportElement {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialContract): R
    fun visit(contract: SupportContract): R
}

class FixedPriceContract(val costPerYear: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R =
        visitor.visit(this)
}

class TimeAndMaterialContract(val costPerHour: Long, val hours: Long) :
    ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R =
        visitor.visit(this)
}

class SupportContract(val costPerMonth: Long) :
    ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R =
        visitor.visit(this)
}

class MonthlyReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long =
        contract.costPerYear / 12

    override fun visit(contract: TimeAndMaterialContract): Long =
        contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long =
        contract.costPerMonth
}

class YearlyReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long =
        contract.costPerYear

    override fun visit(contract: TimeAndMaterialContract): Long =
        contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long =
        contract.costPerMonth * 12
}

class VisitorTest {
    @Test
    fun testVisifor() {
        val projectAlpha = FixedPriceContract(10_000)
        val projectBeta = SupportContract(5_000)
        val projectGamma = TimeAndMaterialContract(150, 10)

        val project = arrayListOf(projectAlpha, projectBeta, projectGamma)
        val monthlyCostVisitor = MonthlyReportVisitor()
        val montlyCost = project.sumOf {
            it.accept(monthlyCostVisitor) }
        println("Monthly cost: $montlyCost")
        assertThat(montlyCost).isEqualTo(7333)

        val yearlyCostVisitor = YearlyReportVisitor()
        val yearlyCost = project.sumOf {
            it.accept(yearlyCostVisitor) }
        println("Yearly cost: $yearlyCost")
        assertThat(yearlyCost).isEqualTo(71_500)
    }
}