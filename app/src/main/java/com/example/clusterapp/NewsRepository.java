package com.example.clusterapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsRepository {

    public static List<NewsArticle> getMockArticles() {
        List<NewsArticle> articles = new ArrayList<>();
        
        String[] techTitles = {
            "Apple's Next-Gen M4 Chips to Focus on AI Performance",
            "Samsung Galaxy S25 Ultra Leaks: A New Design Direction",
            "Quantum Computing Breakthrough: IBM Achieves New Milestones",
            "The Rise of Solid-State Batteries in 2026 Electric Vehicles",
            "Google Chrome's New Privacy Features to Block Advanced Tracking",
            "Windows 12: Everything We Know About the AI-Powered OS",
            "Meta Unveils High-End Mixed Reality Headset to Rival Vision Pro",
            "Tesla's Optimus Gen 3 Bot Demonstrates Human-Like Dexterity",
            "SpaceX Starlink Reaches 10 Million Global Subscribers",
            "New ARM-Based Laptops Are Disrupting the PC Market"
        };

        String[] gamingTitles = {
            "Elder Scrolls VI: Set for a 2026 Reveal?",
            "PlayStation 6 Rumors: Native 8K and Advanced AI Upscaling",
            "Nintendo Switch 2: Full Backward Compatibility Confirmed by Leaks",
            "The Future of eSports: Virtual Reality Leagues Take Center Stage",
            "Cyberpunk 2077 Sequel Enters Full Production at CD Projekt Red",
            "BioWare's Next Mass Effect: A Return to the Milky Way?",
            "Handheld Gaming PC Market Explodes with New Competitors",
            "FromSoftware Announces New 'Dark Fantasy' IP",
            "Valve's 'Deadlock' Hero Shooter Enters Open Beta",
            "Hideo Kojima Reveals More Gameplay for 'Physint'"
        };

        String[] techSnippets = {
            "Tech giants are pivoting towards local AI processing to ensure user privacy and speed.",
            "A new wave of hardware is set to change how we interact with digital environments forever.",
            "Battery technology is finally catching up to the demands of modern mobile devices.",
            "Security experts warn about new sophisticated phishing attacks targeting cloud storage.",
            "The open-source community continues to challenge proprietary software in the AI space."
        };

        String[] gamingSnippets = {
            "Developers are finding new ways to utilize Unreal Engine 5's Nanite and Lumen technologies.",
            "Subscription services are changing the way players discover and play new indie titles.",
            "Cross-play is becoming a standard feature for all major multiplayer releases this year.",
            "Remakes and remasters continue to dominate the charts as nostalgia drives sales.",
            "Indie developers are pushing the boundaries of storytelling in the horror genre."
        };

        String techContentBase = "In a significant move for the industry, recent reports highlight a massive shift in technological development. Companies are investing billions into infrastructure that supports advanced computational needs. The implications for consumers are vast, ranging from smarter devices to more secure personal data management. As we look towards the next decade, the convergence of AI, hardware efficiency, and high-speed connectivity will define the digital landscape.";
        
        String gamingContentBase = "The gaming industry is currently experiencing a renaissance of creative expression. With powerful new hardware available, developers are no longer constrained by technical limitations. From sprawling open worlds with seamless transitions to intimate character-driven narratives, the quality of digital entertainment has reached an all-time high. Community feedback remains a core pillar of development, ensuring that player voices are heard.";

        Random random = new Random();

        // Generate 100 articles
        for (int i = 1; i <= 100; i++) {
            boolean isTech = random.nextBoolean();
            String title, category, snippet, content;

            if (isTech) {
                category = "TECH";
                title = techTitles[random.nextInt(techTitles.length)] + " (Update " + i + ")";
                snippet = techSnippets[random.nextInt(techSnippets.length)];
                content = techContentBase + "\n\nThis specific update (" + i + ") discusses the nuances of the " + title.toLowerCase() + " and its long-term impact on the global tech market.";
            } else {
                category = "GAMING";
                title = gamingTitles[random.nextInt(gamingTitles.length)] + " (Vol. " + i + ")";
                snippet = gamingSnippets[random.nextInt(gamingSnippets.length)];
                content = gamingContentBase + "\n\nIn this volume (" + i + "), we dive deeper into why " + title + " is currently the most talked-about topic in the gaming community.";
            }

            articles.add(new NewsArticle(title, category, snippet, content));
        }

        return articles;
    }
}
