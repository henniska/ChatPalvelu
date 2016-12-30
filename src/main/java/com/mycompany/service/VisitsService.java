
package com.mycompany.service;

import com.mycompany.domain.Visits;
import com.mycompany.repository.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitsService {
    
    @Autowired
    private VisitsRepository visitsRepository;
    
    public void increaseViews(String title) {
        Visits v = visitsRepository.findByName(title);
        int newViews = v.getViews() + 1;
        v.setViews(newViews);
        visitsRepository.save(v);
    }
    
    public void decreaseViews(String title) {
        Visits v = visitsRepository.findByName(title);
        int newViews = v.getViews() - 1;
        v.setViews(newViews);
        visitsRepository.save(v);
    }
    
    public int getViews(String title) {
        Visits v = visitsRepository.findByName(title);
        return v.getViews();
    }
}
