package ru.realityfamily.pandorabackend.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.realityfamily.pandorabackend.models.Item;
import ru.realityfamily.pandorabackend.models.Role;
import ru.realityfamily.pandorabackend.models.User;
import ru.realityfamily.pandorabackend.repository.ItemRepository;
import ru.realityfamily.pandorabackend.repository.UserRepository;

import java.util.*;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    ItemRepository itemRepository;
    UserRepository userRepository;
    private User admin;
    private User testUser;

    public BootstrapData(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadItem();
        loadAllDebugUsers();



    }

    private void loadItem(){
        Item item1 = new Item("Divan obivan");
        itemRepository.save(item1);

        item1 = new Item("Stul Barnaul");
        itemRepository.save(item1);

        item1 = new Item("Lustra Balustra");
        itemRepository.save(item1);

        item1 = new Item("Shkaf gav");
        itemRepository.save(item1);
    }

    private void loadAllDebugUsers(){
        admin = new User("realityfamilyteam@yandex.ru","realityfamily", 1111L, Role.Admin);
        admin = userRepository.save(admin);

        testUser = new User("test@gmail.com", "testUser", 1111L, Role.User);

        Set<Item> itemSet = new HashSet<>();
        itemRepository.findAll().iterator().forEachRemaining(itemSet::add);
        testUser.setAssignedItems( new LinkedList<>(itemSet));
        testUser = userRepository.save(testUser);

    }
}
