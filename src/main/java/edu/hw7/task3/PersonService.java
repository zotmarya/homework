package edu.hw7.task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class PersonService implements PersonDatabase {

    private Map<Integer, Person> personMap;
    private Map<String, Set<Integer>> nameMap;
    private Map<String, Set<Integer>> addressMap;
    private Map<String, Set<Integer>> phoneMap;
    private static final ReadWriteLock READ_WRITE_LOCK
        = new ReentrantReadWriteLock();
    private Lock readLock;
    private Lock writeLock;

    public PersonService() {
        personMap = new HashMap<>();
        nameMap = new HashMap<>();
        addressMap = new HashMap<>();
        phoneMap = new HashMap<>();
        readLock = READ_WRITE_LOCK.readLock();
        writeLock = READ_WRITE_LOCK.writeLock();
    }

    @Override
    public synchronized void add(Person person) {
        writeLock.lock();
        try {
            int id = person.id();

            personMap.put(id, person);

            nameMap.computeIfAbsent(person.name(), k -> new HashSet<>()).add(id);
            addressMap.computeIfAbsent(person.address(), k -> new HashSet<>()).add(id);
            phoneMap.computeIfAbsent(person.phoneNumber(), k -> new HashSet<>()).add(id);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public synchronized void delete(int id) {
        writeLock.lock();
        try {
            Person person = personMap.remove(personMap.get(id));

            nameMap.get(person.name()).remove(id);
            addressMap.get(person.address()).remove(id);
            phoneMap.get(person.phoneNumber()).remove(id);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return getPeople(name, nameMap);

    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return getPeople(address, addressMap);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return getPeople(phone, phoneMap);
    }

    @Nullable
    private List<Person> getPeople(String key, Map<String, Set<Integer>> map) {
        readLock.lock();
        try {
            Set<Integer> ids = map.get(key);
            List<Person> people = null;

            if (ids != null) {
                people = personMap.entrySet().stream().filter((entry) -> ids.contains(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .toList();
            }

            return people;
        } finally {
            readLock.unlock();
        }
    }
}
