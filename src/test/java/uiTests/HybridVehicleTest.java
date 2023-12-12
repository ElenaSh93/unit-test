package uiTests;

import com.codeborne.selenide.Selenide;
import model.ConfigProperties;
import model.HybridVehiclePage;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HybridVehicleTest extends BaseTest {
    public HybridVehiclePage page = new HybridVehiclePage();
    public int create_record = 0;

    @Test(priority = 1, description = "Тест проверяет наличие справочника \"Тип гибридного транспортного средства\" в разделе справочников",
            testName = "Проверка наличия справочника \"Тип гибридного транспортного средства\" в разделе справочников")
    public void hybrid_test1() {
        page.goToDictionary();
        Assert.assertTrue(page.isDisplayedDictionary(), "Справочник не найден!");
    }

    @Test(priority = 2, description = "Тест проверяет наличие записи \"Комбинированная энергоустановка последовательного типа\"",
            testName = "Проверка наличия записи \"Комбинированная энергоустановка последовательного типа\"")
    public void hybrid_test2() {
        page.goToDictionaryUrl();
        Assert.assertTrue(page.isDisplayedRecord_1(), "не найдено записи \"Комбинированная энергоустановка последовательного типа\"");
    }

    @Test(priority = 3, description = "Тест проверяет наличие записи \"Комбинированная энергоустановка параллельного типа\"",
            testName = "Проверка наличия записи \"Комбинированная энергоустановка параллельного типа\"")
    public void hybrid_test3() {
        Assert.assertTrue(!page.isDisplayedRecord_2(), "не найдено записи \"Комбинированная энергоустановка параллельного типа\"");
    }

    @Test(priority = 4, description = "Тест проверяет наличие атрибута \"Тип гибридного транспортного средства\" в дереве характеристик",
            testName = "Проверка наличия атрибута \"Тип гибридного транспортного средства\" в дереве характеристик")
    public void hybrid_test4() {
        Selenide.refresh();
        page.goSpecificationTree();
        Assert.assertTrue(page.attributeTypeHybridVehicle.isDisplayed(), "не найдено атрибута \"Тип гибридного транспортного средства\"");
    }

    @Test(priority = 5, description = "Тест проверяет тип значения атрибута \"Тип гибридного транспортного средства\" в дереве характеристик",
            testName = "Проверка типа значения атрибута \"Тип гибридного транспортного средства\" в дереве характеристик")
    public void hybrid_test5() {
        var type = page.getTypeAttribute();
        Assert.assertEquals("Справочник", type, "тип значения атрибута \"Тип гибридного транспортного средства\" не справочник");
    }

    @Test(priority = 6, description = "Тест проверяет длину поля атрибута \"Тип гибридного транспортного средства\" в дереве характеристик",
            testName = "Проверка длины поля атрибута \"Тип гибридного транспортного средства\" в дереве характеристик")
    public void hybrid_test6() {
        var length = page.getFieldLengthTypeHybrid();
        Assert.assertEquals("54", length, "длина поля атрибута \"Тип гибридного транспортного средства\" не 54 символа");
    }

    @Test(priority = 7, description = "Тест проверяет длину поля атрибута \"Описание гибридного транспортного средства\" в дереве характеристик",
            testName = "Проверка длины поля атрибута \"Описание гибридного транспортного средства\" в дереве характеристик")
    public void hybrid_test7() {
        var length = page.getFieldLengthDescriptionHybrid();
        Assert.assertEquals("944", length, "длина поля атрибута \"Описание гибридного транспортного средства\" не 944 символа");
    }

    @Test(priority = 8, description = "Тест проверяет наличие атрибута \"Тип гибридного транспортного средства\" в контейнере",
            testName = "Проверка наличия атрибута \"Тип гибридного транспортного средства\" в контейнере")
    public void hybrid_test8() throws InterruptedException {
        Selenide.refresh();
        page.goSpecificationTreeLayout();
        page.getTemplate();
        page.openContainer();
        Assert.assertTrue(page.attributeTypeHybridVehicle.isDisplayed(), "нет атрибута \"Тип гибридного транспортного средства\" в контейнере");
    }

    @Test(priority = 9, description = "Тест проверяет создание записи справочника \"Тип гибридного транспортного средства\"",
            testName = "Проверка создания записи справочника \"Тип гибридного транспортного средства\"")
    public void hybrid_test9() throws InterruptedException {
        page.goToDictionaryUrl();
        page.createRecord();
        Assert.assertTrue(page.created_record.isDisplayed(), "ошибка создания записи справочника \"Тип гибридного транспортного средства\"");
    }

    @Test(priority = 10, description = "Тест проверяет редактирование записи справочника \"Тип гибридного транспортного средства\"",
            testName = "Проверка редактирования записи справочника \"Тип гибридного транспортного средства\"")
    public void hybrid_test10() throws InterruptedException {
        Thread.sleep(2000);
        page.changeRecord();
        Assert.assertTrue(page.modified_record.isDisplayed(), "ошибка редактирования записи справочника \"Тип гибридного транспортного средства\"");
    }

    @Test(priority = 11, description = "Тест проверяет ошибку при привышении длины поля \"Наименование:\" в карточке записи справочника \"Тип гибридного транспортного средства\"",
            testName = "Проверка ошибки при привышении длины поля \"Наименование:\" в карточке записи справочника \"Тип гибридного транспортного средства\"")
    public void hybrid_test11() throws InterruptedException {
        var textError = page.getTextErrorExcessLength();
        Assert.assertEquals("Наименование: Максимальная длина этого поля 54.", textError,
                "не возникло ошибки при привышении длины поля \"Наименование:\" справочника \"Тип гибридного транспортного средства\"");
    }

    @Test(priority = 12, description = "Тест проверяет удаление записи справочника \"Тип гибридного транспортного средства\"",
            testName = "Проверка удаления записи справочника \"Тип гибридного транспортного средства\"")
    public void hybrid_test12() throws InterruptedException {
        Selenide.refresh();
        page.deleteRecord();
        Assert.assertTrue(!page.modified_record.isDisplayed(), "ошибка удаления записи справочника \"Тип гибридного транспортного средства\"");
    }

    @Test(priority = 13, description = "Тест проверяет ошибку изменения статуса ОТТС, когда не активен атрибут \"Тип гибридного транспортного стредства\"",
            testName = "Проверка ошибки изменения статуса ОТТС, когда не активен атрибут \"Тип гибридного транспортного стредства\"")
    public void hybrid_test13() throws InterruptedException {
        page.goToOtts();
        var textError = page.getTextErrorAbsenceAttribute();
        Assert.assertEquals("Изменение статуса запрещено. Атрибут «Тип гибридного транспортного средства» должен быть активен", textError,
                "не возникло ошибки при попытке изменения статуса при не активном атрибуте «Тип гибридного транспортного средства»");
    }

}
