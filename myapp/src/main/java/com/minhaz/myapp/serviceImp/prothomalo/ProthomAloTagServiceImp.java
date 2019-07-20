package com.minhaz.myapp.serviceImp.prothomalo;

import com.minhaz.myapp.dao.TagRepository;
import com.minhaz.myapp.entity.Post;
import com.minhaz.myapp.entity.Tag;
import com.minhaz.myapp.service.NewsPaperService;
import com.minhaz.myapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.HashSet;

@Service
public class ProthomAloTagServiceImp implements TagService {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    @Qualifier("prothomAloServiceImp")
    NewsPaperService newsPaperService;

    @Override
    public void manageCatAndTags(String cat,Post post,String id) throws Exception{
        if(cat.equals("bangladesh")){
            post.setCat("bangladesh");
            assignTagForBDPosts(post,id);
        }

        if(cat.equals("international")){
            post.setCat("international");
            assignTagForIntPosts(post,id);
        }
        if(cat.equals("economy")){
            post.setCat("economy");
        }
        if(cat.equals("opinion")){
            post.setCat("opinion");
        }
        if(cat.equals("sports")){
            post.setCat("sports");
        }
        if(cat.equals("entertainment")){
            post.setCat("entertainment");
        }
        if(cat.equals("technology")){
            post.setCat("technology");
        }
        if(cat.equals("durporobash")){
            post.setCat("durporobash");
        }
    }

    //This method will assign more sub category/tag for bangladesh news news
    @Override
    public void assignTagForBDPosts(Post post, String id) throws Exception {
        HashSet<String> bdPolitics = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-politics");
        HashSet<String> govt = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-government");
        HashSet<String> crime = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-crime");
        HashSet<String> justicLaw = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-justice");
        HashSet<String> environment = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-environment");
        HashSet<String> accident = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-accident");
        HashSet<String> parliament = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-parliament");
        HashSet<String> capital = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-capitalcity");
        HashSet<String> population = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-population");
        HashSet<String> others = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-others");
        HashSet<String> dhaka = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-dhaka");
        HashSet<String> chattogram = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-chattogram");
        HashSet<String> sylhet = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-sylhet");
        HashSet<String> khulna = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-khulna");
        HashSet<String> rajshahi = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-rajshahi");
        HashSet<String> barishal = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-barishal");
        HashSet<String> rangpur = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-rangpur");
        HashSet<String> mymensing = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-mymensingh");
        HashSet<String> bd_others = newsPaperService.findPostIds("https://www.prothomalo.com/bangladesh-others");


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
        if (bd_others.contains(id)) {
            tags.add(getTag("others"));
        }
        post.setTags(tags);
    }

    //This method will assign more sub category/tag for international news news
    @Override
    public void assignTagForIntPosts(Post post, String id) throws Exception {
        HashSet<String> usa = newsPaperService.findPostIds("https://www.prothomalo.com/international-usa");
        HashSet<String> uk = newsPaperService.findPostIds("https://www.prothomalo.com/international-uk");
        HashSet<String> canada = newsPaperService.findPostIds("https://www.prothomalo.com/international-canada");
        HashSet<String> india = newsPaperService.findPostIds("https://www.prothomalo.com/international-india");
        HashSet<String> pakistan = newsPaperService.findPostIds("https://www.prothomalo.com/international-pakistan");
        HashSet<String> asia = newsPaperService.findPostIds("https://www.prothomalo.com/international-asia");
        HashSet<String> europe = newsPaperService.findPostIds("https://www.prothomalo.com/international-europe");
        HashSet<String> australia = newsPaperService.findPostIds("https://www.prothomalo.com/international-australia");
        HashSet<String> africa = newsPaperService.findPostIds("https://www.prothomalo.com/international-africa");
        HashSet<String> arabworld = newsPaperService.findPostIds("https://www.prothomalo.com/international-arabworld");
        HashSet<String> latin = newsPaperService.findPostIds("https://www.prothomalo.com/international-latinamerica");
        HashSet<String> un = newsPaperService.findPostIds("https://www.prothomalo.com/international-unitednations");
        HashSet<String> int_others = newsPaperService.findPostIds("https://www.prothomalo.com/international-others");

        HashSet<Tag> tags = new HashSet<>();
        if (usa.contains(id)) {
            tags.add(getTag("usa"));
        }
        if (uk.contains(id)) {
            tags.add(getTag("uk"));
        }
        if (canada.contains(id)) {
            tags.add(getTag("canada"));
        }
        if (india.contains(id)) {
            tags.add(getTag("india"));
        }
        if (pakistan.contains(id)) {
            tags.add(getTag("pakistan"));
        }
        if (asia.contains(id)) {
            tags.add(getTag("asia"));
        }
        if (europe.contains(id)) {
            tags.add(getTag("europe"));
        }
        if (australia.contains(id)) {
            tags.add(getTag("australia"));
        }
        if (africa.contains(id)) {
            tags.add(getTag("africa"));
        }
        if (arabworld.contains(id)) {
            tags.add(getTag("arabworld"));
        }
        if (latin.contains(id)) {
            tags.add(getTag("latin"));
        }
        if (un.contains(id)) {
            tags.add(getTag("un"));
        }
        if (int_others.contains(id)) {
            tags.add(getTag("others"));
        }
        post.setTags(tags);
    }

    @Override
    public void assignTagForEconomy(Post post, String id) throws Exception {
        HashSet<String> shareMkt = newsPaperService.findPostIds("https://www.prothomalo.com/economy-stocks-markets");
        HashSet<String> business = newsPaperService.findPostIds("https://www.prothomalo.com/economy-business");
        HashSet<String> garments = newsPaperService.findPostIds("https://www.prothomalo.com/economy-garments");
        HashSet<String> budget = newsPaperService.findPostIds("https://www.prothomalo.com/budget");
        HashSet<String> economy_crime = newsPaperService.findPostIds("https://www.prothomalo.com/economy-crime");
        HashSet<String> economy_analysis = newsPaperService.findPostIds("https://www.prothomalo.com/economy-analysis");
        HashSet<String> economy_others = newsPaperService.findPostIds("https://www.prothomalo.com/economy-others");
        HashSet<String> economy_int = newsPaperService.findPostIds("https://www.prothomalo.com/economy-international");

        HashSet<Tag> tags = new HashSet<>();
        if (shareMkt.contains(id)) {
            tags.add(getTag("shareMkt"));
        }
        if (business.contains(id)) {
            tags.add(getTag("business"));
        }
        if (garments.contains(id)) {
            tags.add(getTag("garments"));
        }
        if (budget.contains(id)) {
            tags.add(getTag("budget"));
        }
        if (economy_crime.contains(id)) {
            tags.add(getTag("economy_crime"));
        }
        if (economy_analysis.contains(id)) {
            tags.add(getTag("economy_analysis"));
        }
        if (economy_others.contains(id)) {
            tags.add(getTag("economy_others"));
        }
        if (economy_int.contains(id)) {
            tags.add(getTag("economy_int"));
        }
        post.setTags(tags);
    }

    @Override
    public void assignTagForOpinion(Post post, String id) throws Exception {
        HashSet<String> editorial = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-editorial");
        HashSet<String> opinion_politics = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-politics");
        HashSet<String> opinion_economy = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-economy");
        HashSet<String> opinion_religion = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-religion");
        HashSet<String> opinion_society = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-society");
        HashSet<String> opinion_int = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-international");
        HashSet<String> opinion_edu = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-education");
        HashSet<String> opinion_interview = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-interview");
        HashSet<String> opinion_debate = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-debate");
        HashSet<String> opinion_letter = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-letter");
        HashSet<String> opinion_others = newsPaperService.findPostIds("https://www.prothomalo.com/opinion-others");

        HashSet<Tag> tags = new HashSet<>();
        if (editorial.contains(id)) {
            tags.add(getTag("editorial"));
        }
        if (opinion_politics.contains(id)) {
            tags.add(getTag("opinion_politics"));
        }
        if (opinion_economy.contains(id)) {
            tags.add(getTag("opinion_economy"));
        }
        if (opinion_religion.contains(id)) {
            tags.add(getTag("opinion_religion"));
        }
        if (opinion_society.contains(id)) {
            tags.add(getTag("opinion_society"));
        }
        if (opinion_int.contains(id)) {
            tags.add(getTag("opinion_int"));
        }
        if (opinion_edu.contains(id)) {
            tags.add(getTag("opinion_edu"));
        }
        if (opinion_interview.contains(id)) {
            tags.add(getTag("opinion_interview"));
        }
        if (opinion_debate.contains(id)) {
            tags.add(getTag("opinion_debate"));
        }
        if (opinion_debate.contains(id)) {
            tags.add(getTag("opinion_debate"));
        }
        if (opinion_letter.contains(id)) {
            tags.add(getTag("opinion_letter"));
        }
        if (opinion_letter.contains(id)) {
            tags.add(getTag("opinion_letter"));
        }
        if (opinion_others.contains(id)) {
            tags.add(getTag("opinion_others"));
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
