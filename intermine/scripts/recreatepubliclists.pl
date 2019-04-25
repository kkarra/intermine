#!/usr/bin/perl

use strict;
use warnings;
use Webservice::InterMine;
 
#step9: need to login to the webapp to re-populate the lists

my $appurl = "http://yeastmine.yeastgenome.org/yeastmine/";
my $token = "C113ZemeY4IaY9baCeZe";
my $service = Webservice::InterMine->get_service($appurl, $token);

$service->delete_lists("Uncharacterized_Verified_ORFs", "ALL_Verified_Uncharacterized_Dubious_ORFs", "Verified_ORFs", "Dubious_ORFs", "Uncharacterized_ORFs", "Long Terminal Repeat", "Telomeres", "RetroTransposons", "NotPhysicallyMapped", "Centromeres", "ARSs", "tRNAs", "All Curated Macromolecular Complexes", "Human genes with yeast homologs", "Human genes complementing or complemented by yeast genes");

# there may be a better way to do this...??

### Verified_ORFs ###
my $query1 = $service->new_query;
$query1->add_view(
      'Gene.primaryIdentifier',
);
$query1->add_constraint(
      path  => 'Gene.qualifier',
      op    => '=',
      value => 'Verified',
);
my $new_list_1 = $service->new_list(
    content => $query1, type => "Gene", name => "Verified_ORFs", tags => ["im:public"], description => [""]);

### Dubious_ORF ###
my $query2 = $service->new_query;
$query2->add_view(
      'Gene.primaryIdentifier',
);
$query2->add_constraint(
      path  => 'Gene.qualifier',
      op    => '=',
      value => 'Dubious',
);
my $new_list_2 = $service->new_list(
    content => $query2, type => "Gene", name => "Dubious_ORFs", tags => ["im:public"], description => [""]);


### Uncharacterized_ORFs ###
my $query10 = $service->new_query;
$query10->add_view(
      'Gene.primaryIdentifier',
);
$query10->add_constraint(
      path  => 'Gene.qualifier',
      op    => '=',
      value => 'Uncharacterized',
);
my $new_list_10 = $service->new_list(
    content => $query10, type => "Gene", name => "Uncharacterized_ORFs", tags => ["im:public"],  description => [""]);


### Telomeres ###
my $query5 = $service->new_query;
$query5->add_view(
      'Telomere.primaryIdentifier',
);
my $new_list_5 = $service->new_list(
    content => $query5, type => "Telomere", name => "Telomeres", tags => ["im:public", "im:frontpage"],  description => [""]);



### RetroTransposons ###
my $query6 = $service->new_query;
$query6->add_view(
      'Retrotransposon.primaryIdentifier',
);
my $new_list_6 = $service->new_list(
    content => $query6, type => "Retrotransposon", name => "RetroTransposons", tags => ["im:public", "im:frontpage"],  description => [""]);


### NotPhysicallyMapped ###
my $query7 = $service->new_query;
$query7->add_view(
      'NotPhysicallyMapped.primaryIdentifier',
);
my $new_list_7 = $service->new_list(
    content => $query7, type => "NotPhysicallyMapped", name => "NotPhysicallyMapped", tags => ["im:public" , "im:frontpage"],  description => [""]);

### Centromeres ###
my $query8 = $service->new_query;
$query8->add_view(
      'Centromere.primaryIdentifier',
);
my $new_list_8 = $service->new_list(
    content => $query8, type => "Centromere", name => "Centromeres", tags => ["im:public", "im:frontpage"],  description => [""]);


### ARSs ###
my $query9 = $service->new_query;
$query9->add_view(
      'ARS.primaryIdentifier',
);
my $new_list_9 = $service->new_list(
    content => $query9, type => "ARS", name => "ARSs", tags => ["im:public"],  description => [""]);


### tRNAs ###
my $query3 = $service->new_query;
$query3->add_view(
      'TRNAGene.primaryIdentifier',
);
my $new_list_3 = $service->new_list(
    content => $query3, type => "TRNAGene", name => "tRNAs", tags => ["im:public"],  description => [""]);

### Long Terminal Repeats  ###
my $query4 = $service->new_query;
$query4->add_view(
      'LongTerminalRepeat.primaryIdentifier',
);
$query4->add_constraint(
      path  => 'LongTerminalRepeat.status',
      op    => '=',
      value => 'Active',
);
my $new_list_4 = $service->new_list(
    content => $query4, type => "LongTerminalRepeat", name => "Long Terminal Repeat", tags => ["im:public", "im:frontpage"],  description => [""]);

###Uncharacterized_Verified_ORFs###

my $queryunver = $service->new_query;
$queryunver->add_view(
      'Gene.primaryIdentifier',
);
$queryunver->where(
      qualifier => ['Verified','Uncharacterized']
);
my $neworflist = $service->new_list(
    content => $queryunver, type => "Gene", name => "Uncharacterized_Verified_ORFs", tags => ["im:public", "im:frontpage"],  description => ["This List excludes Dubious ORFs"]);

### ALL_Verified_Uncharacterized_Dubious_ORFs ###

my $queryorfs = $service->new_query;

$queryorfs->add_view(
      'Gene.primaryIdentifier',
);
$queryorfs->where(
      qualifier => ['Verified','Uncharacterized', 'Dubious']
);
my $newallorfs = $service->new_list(
    content => $queryorfs, type => "Gene", name => "ALL_Verified_Uncharacterized_Dubious_ORFs", tags => ["im:public", "im:frontpage"],  description => ["This List includes ALL ORFs"]);

### Molecular Complexes ###
my $complex = $service->new_query;

$complex->add_view('Complex.identifier', 'Complex.name', 'Complex.systematicName', 'Complex.properties', 'Complex.function');

my $newcomplex = $service->new_list(
     content => $complex, type => "Complex", name => "All Curated Macromolecular Complexes", tags => ["im:public", "im:frontpage"],  description => ["All curated molecular complexes"]);


### human genes with orthologs###
my $orthologs = $service->new_query;
$orthologs->add_view('Gene.primaryIdentifier', 'Gene.secondaryIdentifier', 'Gene.symbol', 'Gene.name');
$orthologs->add_constraint(path => 'Gene.organism.shortName', op => '=', value => 'H. sapiens');
$orthologs->add_constraint(path => 'Gene.homologues.homologue.organism.shortName', op => '=', value => 'S. cerevisiae');
my $neworthologs = $service->new_list(content => $orthologs, type => "Gene", name => "Human genes with yeast homologs", tags => ["im:public", "im:frontpage"], description => [""]);

### human genes that complement###
my $complement = $service->new_query;
$complement->add_view('Gene.primaryIdentifier', 'Gene.secondaryIdentifier', 'Gene.symbol', 'Gene.name');
$complement->add_constraint(path => 'Gene.organism.shortName', op => '=', value => 'H. sapiens');
$complement->add_constraint(path => 'Gene.complements.complement.organism.shortName', op => '=', value => 'S. cerevisiae');
my $newcomplement = $service->new_list(content => $complement, type => "Gene", name => "Human genes complementing or complemented by yeast genes", tags => ["im:public", "im:frontpage"], description => [""]);
my @newlists = $service->get_lists();
print @newlists;
