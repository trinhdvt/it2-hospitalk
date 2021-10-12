package com.team1.it2hospitalk.utils;

import com.github.javafaker.Faker;
import com.team1.it2hospitalk.model.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FakeData {

    private static final Faker faker = new Faker(new Locale("en-US"));

    static List<Message> createSampleMessage(User creator, Channel channel, int amount) {
        List<Message> msg = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            String body = faker.lorem().sentence(15);

            Message message = Message.builder()
                    .creator(creator)
                    .channel(channel)
                    .body(body)
                    .createdAt(new Date())
                    .build();
            msg.add(message);

        }
        return msg;
    }

    static List<Hospital> createSampleHospital(int amount) {
        List<Hospital> hospitals = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Hospital hospital = Hospital.builder()
                    .name(faker.medical().hospitalName())
                    .address(faker.address().fullAddress())
                    .build();

            hospitals.add(hospital);
        }


        return hospitals;
    }

    static List<User> createSampleUser(int amount) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            User user = User.builder()
                    .username(faker.name().username())
                    .password(faker.crypto().sha1())
                    .role(Role.MANAGER)
                    .age(faker.number().numberBetween(20, 50))
                    .fullName(faker.name().fullName())
                    .job(faker.job().position())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .address(faker.address().fullAddress())
                    .avatarUrl(faker.avatar().image())
                    .build();

            userList.add(user);
        }

        return userList;
    }

}
