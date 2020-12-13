import org.drools.core.impl.InternalKnowledgeBase
import org.drools.core.impl.KnowledgeBaseFactory
import org.kie.api.io.ResourceType
import org.kie.internal.builder.KnowledgeBuilderFactory
import org.kie.internal.io.ResourceFactory
import java.io.File

fun main() {
    val kbase = readKnowledgeBase(listOf(File("/Users/macbookpro152019/university/poisLabs/lab22/src/main/resources/rules/Devices.drl")))
    val kSession = kbase.newKieSession()
    val device = Device("Apple", "Iphone12", 1100)
    val Dima = Customer(1000, curDevice = Device("Apple", "Iphone11", 1000))
    val Fedor = Customer(1000, curDevice = Device("Apple", "Iphone12", 1100))
    val Sasha = Customer(1000, curDevice = Device("Samsung", "S21", 1500))
    val Vasya = Customer(1000, curDevice = Device("Samsung", "S10", 1000))

    kSession.insert(Calcutils())

    kSession.insert(Dima)
    kSession.insert(device)
    kSession.fireAllRules()

    kSession.insert(Fedor)
    kSession.insert(device)
    kSession.fireAllRules()

    kSession.insert(Sasha)
    kSession.insert(device)
    kSession.fireAllRules()

    kSession.insert(Vasya)
    kSession.insert(device)
    kSession.fireAllRules()
}

fun readKnowledgeBase(files: List<File>): InternalKnowledgeBase {
    val knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder()
    files.forEach { i: File? -> knowledgeBuilder.add(ResourceFactory.newFileResource(i), ResourceType.DRL) }
    val errors = knowledgeBuilder.errors
    if (!errors.isEmpty()) {
        for (error in errors) {
            println("{$error}")
        }
        throw IllegalArgumentException("Could not parse knowledge.")
    }
    val kBase = KnowledgeBaseFactory.newKnowledgeBase()
    kBase.addPackages(knowledgeBuilder.knowledgePackages)
    return kBase
}