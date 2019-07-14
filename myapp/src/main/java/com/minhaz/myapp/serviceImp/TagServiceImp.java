package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.dao.TagRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.entity.Tag;
import com.minhaz.myapp.service.ProthomAloService;
import com.minhaz.myapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    ProthomAloService prothomAloService;

    //This method will assign more sub category/tag for a news

    @Override
    public void assignTag(Post post, String id) throws Exception {
        HashSet<String> bdPolitics = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-politics");
        HashSet<String> govt = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-government");
        HashSet<String> crime = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-crime");
        HashSet<String> justicLaw = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-justice");
        HashSet<String> environment = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-environment");
        HashSet<String> accident = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-accident");
        HashSet<String> parliament = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-parliament");
        HashSet<String> capital = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-capitalcity");
        HashSet<String> population = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-population");
        HashSet<String> others = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-others");
        HashSet<String> dhaka = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-dhaka");
        HashSet<String> chattogram = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-chattogram");
        HashSet<String> sylhet = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-sylhet");
        HashSet<String> khulna = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-khulna");
        HashSet<String> rajshahi = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-rajshahi");
        HashSet<String> barishal = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-barishal");
        HashSet<String> rangpur = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-rangpur");
        HashSet<String> mymensing = prothomAloService.findPostIds("https://www.prothomalo.com/bangladesh-mymensingh");


        HashSet<Tag> tags = new HashSet<>();
        if (bdPolitics.contains(id)) {
            tags.add(getTag("bd_politics"));
        }
        if (govt.contains(id)) {
            tags.add(getTag("govt"));
        }
        if (crime.contains(id)) {
            tags.add(getTag("crime"));
        }
        if (justicLaw.contains(id)) {
            tags.add(getTag("justice_law"));
        }
        if (environment.contains(id)) {
            tags.add(getTag("environment"));
        }
        if (accident.contains(id)) {
            tags.add(getTag("accident"));
        }
        if (parliament.contains(id)) {
            tags.add(getTag("parliament"));
        }
        if (capital.contains(id)) {
            tags.add(getTag("capital"));
        }
        if (population.contains(id)) {
            tags.add(getTag("population"));
        }
        if (others.contains(id)) {
            tags.add(getTag("others"));
        }
        if (dhaka.contains(id)) {
            tags.add(getTag("dhaka"));
        }
        if (chattogram.contains(id)) {
            tags.add(getTag("chattogram"));
        }
        if (sylhet.contains(id)) {
            tags.add(getTag("sylhet"));
        }
        if (khulna.contains(id)) {
            tags.add(getTag("khulna"));
        }
        if (rajshahi.contains(id)) {
            tags.add(getTag("rajshahi"));
        }
        if (barishal.contains(id)) {
            tags.add(getTag("barishal"));
        }
        if (rangpur.contains(id)) {
            tags.add(getTag("rangpur"));
        }
        if (mymensing.contains(id)) {
            tags.add(getTag("mymensing"));
        }
        post.setTags(tags);
    }

    private Tag getTag(String tagName){
        Tag tag = tagRepository.findByTagName(tagName);
        if(tag == null){
            tag = new Tag(tagName);
            tagRepository.save(tag);
            tag = tagRepository.findByTagName(tagName);
        }
        return tag;
    }

}
