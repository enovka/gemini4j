package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the categories of potential harm that the Gemini API can assess in
 * generated content. These categories provide a nuanced classification of
 * content that may be considered unsafe, inappropriate, or offensive.
 * Developers can use these categories to configure the safety settings of their
 * Gemini API requests, allowing them to fine-tune the level of content
 * filtering and moderation applied to the generated responses.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum HarmCategoryEnum {
    /**
     * **Unspecified Harm Category:** The harm category is not explicitly
     * defined. This value is typically used as a default or placeholder when a
     * specific harm category is not applicable.
     */
    @JsonProperty("HARM_CATEGORY_UNSPECIFIED")
    HARM_CATEGORY_UNSPECIFIED,

    /**
     * **Derogatory Content:** Negative or harmful comments that target a
     * person's identity or protected attributes, such as race, religion,
     * gender, sexual orientation, or disability. This category encompasses
     * content that is discriminatory, prejudiced, or offensive based on
     * personal characteristics.
     * <br>**Example:** "People of that race are all lazy."
     */
    @JsonProperty("HARM_CATEGORY_DEROGATORY")
    HARM_CATEGORY_DEROGATORY,

    /**
     * **Toxic Content:** Content that is rude, disrespectful, or uses profane
     * language. This category includes insults, threats, harassment, and other
     * forms of verbal abuse.
     * <br>**Example:** "You're an idiot! Shut up!"
     */
    @JsonProperty("HARM_CATEGORY_TOXICITY")
    HARM_CATEGORY_TOXICITY,

    /**
     * **Violent Content:** Content depicting violence against an individual or
     * group, or containing graphic descriptions of gore, injury, or death. This
     * category encompasses content that is disturbing, harmful, or promotes
     * violence.
     * <br>**Example:** "He brutally attacked the victim with a knife."
     */
    @JsonProperty("HARM_CATEGORY_VIOLENCE")
    HARM_CATEGORY_VIOLENCE,

    /**
     * **Sexual Content:** Content containing sexually suggestive or explicit
     * material, including nudity, pornography, or discussions of sexual acts.
     * This category is used to identify content that is not appropriate for all
     * audiences.
     * <br>**Example:** "The website contained explicit images."
     */
    @JsonProperty("HARM_CATEGORY_SEXUAL")
    HARM_CATEGORY_SEXUAL,

    /**
     * **Medical Content:** Content promoting potentially harmful or misleading
     * medical advice, diagnoses, or treatments. This category helps to identify
     * content that could have negative health consequences if followed without
     * consulting qualified medical professionals.
     * <br>**Example:** "Drinking bleach can cure COVID-19."
     */
    @JsonProperty("HARM_CATEGORY_MEDICAL")
    HARM_CATEGORY_MEDICAL,

    /**
     * **Dangerous Content:** Content that encourages, facilitates, or promotes
     * dangerous or illegal activities, such as drug use, self-harm, or criminal
     * acts. This category helps to prevent the spread of harmful information
     * and protect users from potential harm.
     * <br>**Example:** "Here's how to build a bomb."
     */
    @JsonProperty("HARM_CATEGORY_DANGEROUS")
    HARM_CATEGORY_DANGEROUS,

    /**
     * **Harassment Content:** Content that constitutes harassment or bullying,
     * including personal attacks, threats, intimidation, or stalking. This
     * category protects individuals from online abuse and promotes a respectful
     * online environment.
     * <br>**Example:** "I'm going to find you and hurt you."
     */
    @JsonProperty("HARM_CATEGORY_HARASSMENT")
    HARM_CATEGORY_HARASSMENT,

    /**
     * **Hate Speech Content:** Content expressing hatred or prejudice against a
     * specific group based on their race, religion, ethnicity, gender, sexual
     * orientation, or other protected characteristics. This category
     * encompasses content that is discriminatory, incites violence, or promotes
     * intolerance.
     * <br>**Example:** "All members of that religion are terrorists."
     */
    @JsonProperty("HARM_CATEGORY_HATE_SPEECH")
    HARM_CATEGORY_HATE_SPEECH,

    /**
     * **Sexually Explicit Content:**  Content that is explicitly sexual in
     * nature and may include graphic descriptions of sexual acts, nudity, or
     * pornography. This category is used to filter out content that is not
     * suitable for all audiences and may be considered offensive or harmful.
     * <br>**Example:** A detailed description of a sexual act.
     */
    @JsonProperty("HARM_CATEGORY_SEXUALLY_EXPLICIT")
    HARM_CATEGORY_SEXUALLY_EXPLICIT,

    //TODO google not specific information's about this in your documentation
    @JsonProperty("HARM_CATEGORY_CIVIC_INTEGRITY")
    HARM_CATEGORY_CIVIC_INTEGRITY,
    /**
     * **Dangerous Content:** Content posing a significant risk of harm or
     * danger to individuals or society, such as instructions for building
     * weapons, promoting self-harm, or inciting violence. This category
     * encompasses content that is considered highly dangerous and should be
     * blocked to protect users.
     * <br>**Example:** "Detailed instructions for making a homemade explosive
     * device."
     */
    @JsonProperty("HARM_CATEGORY_DANGEROUS_CONTENT")
    HARM_CATEGORY_DANGEROUS_CONTENT
}