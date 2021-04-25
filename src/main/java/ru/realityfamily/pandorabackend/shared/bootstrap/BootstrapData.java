package ru.realityfamily.pandorabackend.shared.bootstrap;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.realityfamily.pandorabackend.shared.models.*;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;
import ru.realityfamily.pandorabackend.shared.repository.ItemRepository;
import ru.realityfamily.pandorabackend.shared.repository.SubcategoryRepository;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;

import java.io.File;
import java.util.*;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    ItemRepository itemRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    SubcategoryRepository subcategoryRepository;
    private User admin;
    private User testUser;

    public BootstrapData(ItemRepository itemRepository, UserRepository userRepository, CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadItem();
        loadAllDebugUsers();
        loadAllDebugCategoriesAndSubcategories();
    }

    private void loadItem(){
        Item item1 = new Item("Divan obivan");
        item1.setId("6073182a864dbb4531677d3f");
        itemRepository.save(item1);

        item1 = new Item("Stul Barnaul");
        item1.setId("6073182a864dbb4531677d40");
        itemRepository.save(item1);

        item1 = new Item("Lustra Balustra");
        item1.setId("6073182a864dbb4531677d41");
        itemRepository.save(item1);

        item1 = new Item("Shkaf gav");
        item1.setId("6073182a864dbb4531677d42");
        itemRepository.save(item1);

        item1 = new Item("Lol kek cheburek");
        saveIfNotExist(item1, itemRepository);
    }

    private void loadAllDebugUsers(){
        admin = new User("realityfamilyteam@yandex.ru","realityfamily", 1111L, Role.Admin);
        admin.setId("607300ddbd25bf1b1101561b");
        saveIfNotExist(admin, userRepository);

        testUser = new User("test@gmail.com", "testUser", 1111L, Role.User);
        testUser.setId("607300ddbd25bf1b1101561c");
        saveIfNotExist(testUser, userRepository);

    }


    private void loadAllDebugCategoriesAndSubcategories(){

        List<Subcategory> subcategoriesKuhnya = new ArrayList<>();
        subcategoriesKuhnya.add(new Subcategory("Еда,напитки","подкатегория еды и напитков"));
        subcategoriesKuhnya.add(new Subcategory("Кухонные гарнитуры","подкатегория кухонные гарнитуры"));
        subcategoriesKuhnya.add(new Subcategory("Техника крупная","подкатегория техника крупная"));
        subcategoriesKuhnya.add(new Subcategory("Декор","Подкатегория декор"));
        subcategoriesKuhnya.add(new Subcategory("Посуда","подкатегория посуда"));
        subcategoriesKuhnya = saveAllSubcategoryIfNotExist(subcategoriesKuhnya);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Кухня", "Категория для добавления кухонных приборов и их принадлежностей", subcategoriesKuhnya ));

        List<Subcategory> subcategoriesDetskaya = new ArrayList<>();
        subcategoriesDetskaya.add(new Subcategory("Мебель корпусная","подкатегория Мебель корпусная"));
        subcategoriesDetskaya.add(new Subcategory("Кровати и диваны","подкатегория Кровати и диваны"));
        subcategoriesDetskaya.add(new Subcategory("Столы и стулья","подкатегория Столы и стулья"));
        subcategoriesDetskaya.add(new Subcategory("Декор","Подкатегория Декор"));
        subcategoriesDetskaya.add(new Subcategory("Игрушки","подкатегория Игрушки"));
        subcategoriesDetskaya = saveAllSubcategoryIfNotExist(subcategoriesDetskaya);
        categories.add(new Category("Детская", "Категория для добавления новых моделей детских", subcategoriesDetskaya));

        saveAllCategoryIfNotExist(categories);
    }

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(BootstrapData.class);
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }


    private List<Category> saveAllCategoryIfNotExist(List<Category> categories){
        List<Category> categoriesOutput = new ArrayList<>();
        for (Category c : categories){
            categoriesOutput.add(saveCategoryIfNotExist(c));
        }
        return categoriesOutput;
    }

    private Category saveCategoryIfNotExist(Category category){
        boolean a = categoryRepository.exists(Example.of(category));
        if(!a){
            return categoryRepository.save(category);
        }
        else return categoryRepository.findOne(Example.of(category)).get();
    }

    private Subcategory saveSubcategoryIfNotExist(Subcategory subcategory){
        if(!subcategoryRepository.exists(Example.of(subcategory))){
            return subcategoryRepository.save(subcategory);
        }
        else return subcategoryRepository.findOne(Example.of(subcategory)).get();
    }

    private List<Subcategory> saveAllSubcategoryIfNotExist(List<Subcategory> subcategories){
        List<Subcategory> subcategoryOutput = new ArrayList<>();
        for (Subcategory c : subcategories){
            subcategoryOutput.add(saveSubcategoryIfNotExist(c));
        }
        return subcategoryOutput;
    }

    private <T extends BaseMongoTemplate, R extends MongoRepository> T saveIfNotExist(T object, R repository ){
        /*Optional<T> op = repository.findById(object.getId());
        if(op.isPresent()){
            return op.get();
        }else {
            return (T) repository.save(object);
        }*/

        if(!repository.exists(Example.of(object))){
            return (T) repository.save(object);
        }
        else return (T) repository.findOne(Example.of(object)).get();

    }
}
