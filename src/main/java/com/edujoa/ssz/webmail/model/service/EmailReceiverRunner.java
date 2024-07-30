package com.edujoa.ssz.webmail.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class EmailReceiverRunner implements CommandLineRunner{

	@Autowired
    private EmailReceiverService emailReceiverService;

    @Override
    public void run(String... args) throws Exception {
        emailReceiverService.receiveEmails();
    }

}
