package edu.app.util.orderUtils;

import edu.app.model.phone.Computer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component(value = "orderUtils")
public class OrderUtils {

    public double orderSum(List<Computer> computers) {
        return computers.stream().mapToDouble(Computer::getPrice).sum();
    }


    public void addPhone(List<Computer> computers, Computer computer) {
        computers.add(computer);
    }


    public boolean containsPhone(List<Computer> computers, long id) {
        Computer computer2 = computers.stream().filter(phone -> phone.getId() == id)
                .findFirst()
                .orElse(null);

        return computer2 != null;
    }


    public void deletePhone(List<Computer> computers, long id) {
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                computers.remove(computer);
                return;
            }
        }
    }


    public void deleteAllPhones(List<Computer> computers, long id) {
        computers.removeIf(phone -> phone.getId() == id);

    }


    public Map<String, Integer> orderInformation(List<Computer> computers) {
        Map<String, Integer> order = new HashMap<>();

        for (Computer computer : computers) {
            Integer value = order.get(computer.getName());
            if (value == null) {
                order.put(computer.getName(), 1);
            } else {
                order.put(computer.getName(), ++value);
            }
        }
        return order;
    }


    public Map<String, Map<Computer, Integer>> convertListOfPhonesIntoMap(List<Computer> userComputers) {
        Map<String, Map<Computer, Integer>> phones = new HashMap<>();
        for (Computer computer : userComputers) {
            Map<Computer, Integer> s = new HashMap<>();
            if (phones.containsKey(computer.getName())) {
                s = phones.get(computer.getName());
                Set<Computer> set = s.keySet();
                Computer p = (Computer) set.toArray()[0];
                int i = s.get(p) + 1;
                s.put(p, i);
            } else {
                s.put(computer, 1);
            }
            phones.put(computer.getName(), s);
        }
        return phones;
    }
}