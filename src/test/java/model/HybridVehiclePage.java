package model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HybridVehiclePage extends BasePage {
    public SelenideElement dictionaries = $(byText("Справочники"));
    public SelenideElement dictionaryTypeHybridVehicle = $(byText("Тип гибридного транспортного средства"));
    public SelenideElement attributeTypeHybridVehicle = $(byText("Тип гибридного транспортного средства"));
    public SelenideElement btnClearSearchForm = $(byText("Очистить форму поиска"));
    public SelenideElement formSelectCategory = $(byText("Выбор элемента"));
    public SelenideElement btnSelect = $$(byText("Выбрать")).filter(Condition.visible).first();
    public ElementsCollection listApprovalType = $$(By.cssSelector(".x-boundlist li"));
    public SelenideElement btnSearch = $$(byText("Искать")).filter(Condition.visible).first();
    public SelenideElement containerTypeHybridVehicle = $$(byText("Описание гибридного транспортного средства")).filter(Condition.visible).first();
    public SelenideElement blockTypeHybridVehicle = $$(byText("Описание гибридного транспортного средства")).filter(Condition.visible).get(1);
    public SelenideElement btnEdit = $$(byText("Редактировать")).filter(Condition.visible).first();
    public SelenideElement btnCreate = $$(byText("Добавить")).first();
    public SelenideElement btnSave = $$(byText("Сохранить")).filter(Condition.visible).first();
    public SelenideElement created_record = $(byText("Тестовая запись"));
    public SelenideElement modified_record = $(byText("Тестовая запись измененная"));
    public SelenideElement sameDictionary = $(byText("Подзарядка от внешнего источника"));
    public SelenideElement btnArchive = $$(byText("Архивировать")).filter(Condition.visible).first();
    public SelenideElement record_1 = $(byText("Комбинированная энергоустановка последовательного типа"));
    public SelenideElement record_2 = $(byText("Комбинированная энергоустановка параллельного типа"));
    public SelenideElement btnOk = $(byText("OK"));
    public SelenideElement btnYes = $(byText("Да"));
    public SelenideElement app1 = $(byText("Приложение №1 (Общие характеристики ТС)"));
    public SelenideElement startStatus = $(byText("Черновик"));
    public ElementsCollection listStatuses = $$(By.cssSelector(".x-menu-item-text"));
    public SelenideElement btnApply = $$(byText("Применить")).filter(Condition.visible).first();


    public boolean isDisplayedDictionary() {
        return isDisplayed(dictionaryTypeHybridVehicle);
    }
    public void goToDictionary() {
        while (!sameDictionary.isDisplayed()) {
            doubleClick(dictionaries);
        }
        scrollIntoView(sameDictionary);
        click(dictionaryTypeHybridVehicle);
    }

    public boolean isDisplayedRecord_1() {

        return isDisplayed(record_1);
    }
    public boolean isDisplayedRecord_2() {
        return isDisplayed(record_2);
    }
    public SelenideElement getSearchIconByCategory() {

        return getIconSearchByLabelText("Категория:");
    }
    public SelenideElement getArrowIconByApprovalType() {

        return getIconArrowByLabel("Тип одобрения:");
    }

    public SelenideElement getCheckColumnForCategory() {
        var element = formSelectCategory.parent().parent().parent().parent().parent();
        var id = element.getAttribute("id");
        var locator = String.format("#%s .x-grid-checkcolumn", id);
        return $(By.cssSelector(locator));
    }

    public String getTypeAttribute() {
        var element = $$(byText("Тип гибридного транспортного средства")).last();
        return getPropertyAttribute(element,4);
    }

    public String getFieldLengthTypeHybrid() {
        var element = $$(byText("Тип гибридного транспортного средства")).last();
            return getPropertyAttribute(element, 5);
        }
    public String getFieldLengthDescriptionHybrid() {
        var element = $$(byText("Описание гибридного транспортного средства")).get(4);
        return getPropertyAttribute(element, 5);
    }

    public String getPropertyAttribute(SelenideElement element, int index) {
        var parent = element.parent().parent().parent().parent().parent();
        var dataRecordId = parent.getAttribute("data-recordid");
        var locator = String.format("[data-recordid=\"%s\"] td", dataRecordId);
        var columns = $$(By.cssSelector(locator));
        var property = columns.get(index).text();
        return property;
    }

    public void goSpecificationTree() {
        Selenide.open(ConfigProperties.getProperty("urlTree"));
        doubleClick(containerTypeHybridVehicle);     //контейнер
        doubleClick(blockTypeHybridVehicle);     //блок
    }
    public void goSpecificationTreeLayout() {
        Selenide.open(ConfigProperties.getProperty("urlTreeLayout"));
        wait(btnClearSearchForm);
    }

    public void goToDictionaryUrl() {
        Selenide.open(ConfigProperties.getProperty("urlDict"));
        wait(btnArchive);
    }

    public void goToOtts() throws InterruptedException {
        Selenide.open(ConfigProperties.getProperty("urlOtts"));
        waitLoading();
    }

    public void selectCategory() throws InterruptedException {
        getSearchIconByCategory().click();
        wait(formSelectCategory);
        getCheckColumnForCategory().click();
        btnSelect.click();
        waitClose(formSelectCategory);
    }

    public void selectApprovalType() {
        getArrowIconByApprovalType().click();
        // тут возможно нужно добавить ожидание
        listApprovalType.get(0).click();
    }

    public void getTemplate() throws InterruptedException {
        selectCategory();
        selectApprovalType();
        click(btnSearch);
        waitLoading();
    }

    public void openContainer() {
        doubleClick(containerTypeHybridVehicle);
        doubleClick(blockTypeHybridVehicle);
    }

    public SelenideElement getFieldByLabelText(String text) {
        var labelEl = $(byText(text)).parent().parent();
        var fieldId = labelEl.getAttribute("for");
        return $(By.id(fieldId));
    }

    public SelenideElement getFormByText(String text) {
        return $$(By.cssSelector(".x-title-text.x-title-text-desktop-window"))
                .filter(Condition.text(text)).first();
    }

    public void createRecord() throws InterruptedException {
        click(btnCreate);
        var form = getFormByText("Тип гибридного транспортного средства");
        wait(form);
        getFieldByLabelText("Код:").sendKeys("100");
        getFieldByLabelText("Наименование:").sendKeys("Тестовая запись");
        getFieldByLabelText("Дата начала действия:").sendKeys("05.12.2023");
        getFieldByLabelText("Дата окончания действия:").sendKeys("31.12.2033");
        btnSave.click();
        waitClose(form);
    }

    public void changeRecord() throws InterruptedException {
        created_record.click();
        btnEdit.click();
        var form = getFormByText("Тип гибридного транспортного средства");
        wait(form);
        // сюда возможно нужно будет добавить ожидание
        getFieldByLabelText("Наименование:").sendKeys(" измененная");
        btnSave.click();
        waitClose(form);
    }

    public String getTextErrorExcessLength() throws InterruptedException {
        modified_record.click();
        btnEdit.click();
        var form = getFormByText("Тип гибридного транспортного средства");
        wait(form);
        getFieldByLabelText("Наименование:").sendKeys("10символов10символов10символов10символов");
        btnSave.click();
        var formError = getFormByText("Ошибка сохранения!");
        wait(formError);
        var text = getTextError(formError, "id", "#messagebox-%s-msg > ul > li");
        return text;
    }

    public String getTextErrorAbsenceAttribute() {
        startStatus.click();
        listStatuses.get(0).click();
        var form = getFormByText("Изменение статуса");
        wait(form);
        btnApply.click();
        var formAttention = getFormByText("Внимание!");
        wait(formAttention);
        var text = getTextError(formAttention, "id", "#messagebox-%s-msg");
        return text;
    }

    public void deleteRecord() throws InterruptedException {
        modified_record.click();
        btnArchive.click();
        var form = getFormByText("Внимание!");
        wait(form);
        btnYes.click();
        waitClose(form);
    }

    public void goToContainer() throws InterruptedException {
        app1.doubleClick();
        containerTypeHybridVehicle.click();
        waitLoading();
    }

    public void hardClick(SelenideElement element) {
        var id = element.getAttribute("id");
        var jsCode = String.format("document.querySelector('#%s').click()", id);
        executeJavaScript(jsCode);
    }

}
